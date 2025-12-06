/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import builder.PCBuilderController;
import inventory.Inventaris;
import order.OrderManager;
import simulation.SimulatorEngine;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    private Inventaris inventaris;
    private OrderManager orderManager;
    private SimulatorEngine simulatorEngine;
    private PCBuilderController pcBuilderController;

    private TabelInventarisPanel panelInventaris;
    private TabelPesananMenungguPanel panelPesananMenunggu;
    private TabelPesananSiapRakitPanel panelPesananSiapRakit;
    private PCConfiguratorPanel panelPCConfigurator;

    private JButton btnStartSim;
    private JButton btnStopSim;
    private JButton btnRefresh;

    private Timer autoRefreshTimer;

    public MainDashboard() {
        setTitle("Simulasi Ekosistem Toko Komputer Cerdas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        initCoreLogic();
        initComponents();
        initLayout();
        initEvents();
        initAutoRefresh();
    }

    private void initCoreLogic() {
        inventaris = new Inventaris();
        orderManager = new OrderManager(inventaris);
        simulatorEngine = new SimulatorEngine(inventaris, orderManager);
        pcBuilderController = new PCBuilderController();
    }

    private void initComponents() {
        btnStartSim = new JButton("Start Simulation");
        btnStopSim = new JButton("Stop Simulation");
        btnRefresh = new JButton("Refresh");

        panelInventaris = new TabelInventarisPanel(inventaris);
        panelPesananMenunggu = new TabelPesananMenungguPanel(orderManager);
        panelPesananSiapRakit = new TabelPesananSiapRakitPanel(orderManager);
        panelPCConfigurator = new PCConfiguratorPanel(orderManager);
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(btnStartSim);
        topBar.add(btnStopSim);
        topBar.add(btnRefresh);

        add(topBar, BorderLayout.NORTH);

        JSplitPane splitUtama = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitUtama.setResizeWeight(0.35); // 35% kiri, 65% kanan

        JPanel kiri = new JPanel(new BorderLayout());
        kiri.add(panelPCConfigurator, BorderLayout.CENTER);
        kiri.setBorder(BorderFactory.createTitledBorder("PC Builder Pro"));

        JPanel kananAtas = new JPanel(new BorderLayout());
        kananAtas.add(panelInventaris, BorderLayout.CENTER);
        kananAtas.setBorder(BorderFactory.createTitledBorder("Inventaris Komponen"));

        JTabbedPane tabPesanan = new JTabbedPane();
        tabPesanan.addTab("Pesanan Menunggu Komponen", panelPesananMenunggu);
        tabPesanan.addTab("Pesanan Siap Dirakit", panelPesananSiapRakit);

        JPanel kananBawah = new JPanel(new BorderLayout());
        kananBawah.add(tabPesanan, BorderLayout.CENTER);

        JSplitPane splitKanan = new JSplitPane(JSplitPane.VERTICAL_SPLIT, kananAtas, kananBawah);
        splitKanan.setResizeWeight(0.5);

        splitUtama.setLeftComponent(kiri);
        splitUtama.setRightComponent(splitKanan);

        add(splitUtama, BorderLayout.CENTER);
    }

    private void initEvents() {
        btnStartSim.addActionListener(e -> {
            simulatorEngine.start();
            btnStartSim.setEnabled(false);
            btnStopSim.setEnabled(true);
        });

        btnStopSim.addActionListener(e -> {
            simulatorEngine.stop();
            btnStartSim.setEnabled(true);
            btnStopSim.setEnabled(false);
        });

        btnRefresh.addActionListener(e -> refreshAllTables());
    }

    private void initAutoRefresh() {
        autoRefreshTimer = new Timer(2000, e -> refreshAllTables()); // 2 detik
        autoRefreshTimer.start();
    }

    private void refreshAllTables() {
        if (panelInventaris != null) {
            panelInventaris.reloadData();
        }
        if (panelPesananMenunggu != null) {
            panelPesananMenunggu.reloadData();
        }
        if (panelPesananSiapRakit != null) {
            panelPesananSiapRakit.reloadData();
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
        });
    }
}