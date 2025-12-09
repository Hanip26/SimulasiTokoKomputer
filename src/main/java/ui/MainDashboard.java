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
import java.awt.geom.*;

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
    private JLabel lblStatus;
    private Timer autoRefreshTimer;
    private Timer animationTimer;
    private int animationFrame = 0;

    // Modern color palette
    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SECONDARY_COLOR = new Color(59, 130, 246);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color DANGER_COLOR = new Color(239, 68, 68);
    private static final Color WARNING_COLOR = new Color(251, 146, 60);
    private static final Color BG_COLOR = new Color(248, 250, 252);

    public MainDashboard() {
        setTitle("PC Builder Ecosystem Simulator Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1400, 800);
        setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        initCoreLogic();
        initComponents();
        initLayout();
        initEvents();
        initAutoRefresh();
        initAnimation();
    }

    private void initCoreLogic() {
        inventaris = new Inventaris();
        orderManager = new OrderManager(inventaris);
        simulatorEngine = new SimulatorEngine(inventaris, orderManager);
        pcBuilderController = new PCBuilderController();
    }

    private void initComponents() {
        btnStartSim = createModernButton("Start Simulation", SUCCESS_COLOR);
        btnStopSim = createModernButton("Stop Simulation", DANGER_COLOR);
        btnRefresh = createModernButton("Refresh", SECONDARY_COLOR);

        btnStopSim.setEnabled(false);

        lblStatus = new JLabel("System Ready");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblStatus.setForeground(SUCCESS_COLOR);

        panelInventaris = new TabelInventarisPanel(inventaris);
        panelPesananMenunggu = new TabelPesananMenungguPanel(orderManager);
        panelPesananSiapRakit = new TabelPesananSiapRakitPanel(orderManager);
        panelPCConfigurator = new PCConfiguratorPanel(orderManager);
    }

    private JButton createModernButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color buttonColor = color;
                if (!isEnabled()) {
                    buttonColor = new Color(156, 163, 175);
                } else if (getModel().isPressed()) {
                    buttonColor = color.darker();
                } else if (getModel().isRollover()) {
                    buttonColor = color.brighter();
                }

                g2.setColor(buttonColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(180, 40));

        return button;
    }

    private void initLayout() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(BG_COLOR);

        JPanel topBar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, PRIMARY_COLOR,
                        getWidth(), 0, SECONDARY_COLOR
                );
                g2.setPaint(gradient);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        topBar.setLayout(new BorderLayout());
        topBar.setPreferredSize(new Dimension(0, 72));

        JPanel leftControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 18));
        leftControls.setOpaque(false);
        leftControls.add(btnStartSim);
        leftControls.add(btnStopSim);
        leftControls.add(btnRefresh);

        JPanel rightStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 18));
        rightStatus.setOpaque(false);
        rightStatus.add(lblStatus);

        topBar.add(leftControls, BorderLayout.WEST);
        topBar.add(rightStatus, BorderLayout.EAST);

        root.add(topBar, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JSplitPane splitUtama = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitUtama.setOpaque(false);
        splitUtama.setBorder(null);
        splitUtama.setDividerSize(8);
        splitUtama.setContinuousLayout(true);
        splitUtama.setResizeWeight(0.42); // ~40% kiri, 60% kanan

        JPanel leftCard = createCardPanel();
        leftCard.setLayout(new BorderLayout(8, 8));
        leftCard.setMinimumSize(new Dimension(420, 0));

        JLabel leftTitle = new JLabel("PC Builder Pro");
        leftTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        leftTitle.setForeground(new Color(30, 41, 59));
        leftTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));

        leftCard.add(leftTitle, BorderLayout.NORTH);
        leftCard.add(panelPCConfigurator, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 8, 0);

        // INVENTORY
        JPanel inventoryCard = createCardPanel();
        inventoryCard.setLayout(new BorderLayout(5, 5));

        JLabel invTitle = new JLabel("Component Inventory");
        invTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        invTitle.setForeground(new Color(30, 41, 59));
        invTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        inventoryCard.add(invTitle, BorderLayout.NORTH);
        inventoryCard.add(panelInventaris, BorderLayout.CENTER);

        gbc.gridy = 0;
        gbc.weighty = 0.5;
        rightPanel.add(inventoryCard, gbc);

        JPanel ordersCard = createCardPanel();
        ordersCard.setLayout(new BorderLayout(5, 5));

        JLabel ordTitle = new JLabel("Order Management");
        ordTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        ordTitle.setForeground(new Color(30, 41, 59));
        ordTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 4, 0));

        JTabbedPane tabPesanan = new JTabbedPane();
        tabPesanan.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabPesanan.setBackground(Color.WHITE);
        tabPesanan.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 0));
        tabPesanan.addTab("Waiting", panelPesananMenunggu);
        tabPesanan.addTab("Ready", panelPesananSiapRakit);

        ordersCard.add(ordTitle, BorderLayout.NORTH);
        ordersCard.add(tabPesanan, BorderLayout.CENTER);

        gbc.gridy = 1;
        gbc.weighty = 0.5;
        gbc.insets = new Insets(8, 0, 0, 0);
        rightPanel.add(ordersCard, gbc);

        // pasang ke JSplitPane
        splitUtama.setLeftComponent(leftCard);
        splitUtama.setRightComponent(rightPanel);

        mainPanel.add(splitUtama, BorderLayout.CENTER);
        root.add(mainPanel, BorderLayout.CENTER);

        setContentPane(root);
    }

    private JPanel createCardPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 20));
                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 15, 15);

                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, 15, 15);
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        return panel;
    }

    private void initEvents() {
        btnStartSim.addActionListener(e -> {
            simulatorEngine.start();
            btnStartSim.setEnabled(false);
            btnStopSim.setEnabled(true);
            updateStatus("Simulation Running", SUCCESS_COLOR);
            showNotification("Simulation started successfully!", SUCCESS_COLOR);
        });

        btnStopSim.addActionListener(e -> {
            simulatorEngine.stop();
            btnStartSim.setEnabled(true);
            btnStopSim.setEnabled(false);
            updateStatus("Simulation Paused", WARNING_COLOR);
            showNotification("Simulation stopped", WARNING_COLOR);
        });

        btnRefresh.addActionListener(e -> {
            refreshAllTables();
            showNotification("Data refreshed!", SECONDARY_COLOR);
        });
    }

    private void initAutoRefresh() {
        autoRefreshTimer = new Timer(2000, e -> refreshAllTables());
        autoRefreshTimer.start();
    }

    private void initAnimation() {
        animationTimer = new Timer(50, e -> {
            animationFrame++;
            if (lblStatus != null && btnStopSim.isEnabled()) {
                int alpha = (int) (127 + 127 * Math.sin(animationFrame * 0.1));
                lblStatus.setForeground(new Color(SUCCESS_COLOR.getRed(), SUCCESS_COLOR.getGreen(),
                        SUCCESS_COLOR.getBlue(), alpha));
            }
        });
        animationTimer.start();
    }

    private void updateStatus(String text, Color color) {
        lblStatus.setText(text);
        lblStatus.setForeground(color);
    }

    private void showNotification(String message, Color color) {
        JWindow notification = new JWindow(this);
        notification.setAlwaysOnTop(true);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(color);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            }
        };
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel label = new JLabel(message);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(Color.WHITE);
        panel.add(label);

        notification.add(panel);
        notification.pack();

        Point frameLoc = getLocationOnScreen();
        int frameWidth  = getWidth();
        int notifWidth  = notification.getWidth();

        int x = frameLoc.x + (frameWidth - notifWidth) / 2;
        int y = frameLoc.y + 90;

        notification.setLocation(x, y);
        notification.setVisible(true);

        Timer timer = new Timer(2000, ev -> notification.dispose());
        timer.setRepeats(false);
        timer.start();
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