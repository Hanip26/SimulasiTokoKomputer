/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import model.Komponen;
import builder.PCBuilderController;
import builder.IncompatibleComponentException;
import model.*;
import order.OrderManager;
import order.StatusPesanan;

public class PCConfiguratorPanel extends JPanel {

    private final OrderManager orderManager;
    private final PCBuilderController builderController;

    private JComboBox<CPU> comboCPU;
    private JComboBox<Motherboard> comboMotherboard;
    private JComboBox<RAM> comboRAM;
    private JComboBox<GPU> comboGPU;
    private JComboBox<Storage> comboStorage;
    private JComboBox<PowerSupply> comboPSU;

    private JTextArea areaPreview;

    public PCConfiguratorPanel(OrderManager orderManager) {
        this.orderManager = orderManager;
        this.builderController = new PCBuilderController();
        initUI();
    }

    private void initUI() {
        // Layout utama panel
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 247, 250));

        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setOpaque(false);

        JLabel lblTitle = new JLabel("PC Builder Pro");
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 18f));

        JLabel lblSub = new JLabel("Simulasi rakit PC untuk toko komputer cerdas");
        lblSub.setFont(lblSub.getFont().deriveFont(Font.PLAIN, 12f));
        lblSub.setForeground(new Color(90, 90, 90));

        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new BoxLayout(titleWrapper, BoxLayout.Y_AXIS));
        titleWrapper.setOpaque(false);
        titleWrapper.add(lblTitle);
        titleWrapper.add(Box.createVerticalStrut(3));
        titleWrapper.add(lblSub);

        headerPanel.add(titleWrapper, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Form Komponen
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(
                BorderFactory.createTitledBorder("Konfigurasi Komponen")
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 8, 5, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;

        comboCPU = new JComboBox<>(new CPU[]{
                new CPU("Intel Core i3-12100F", "LGA1700", 4, 3.3),
                new CPU("Intel Core i5-12400F", "LGA1700", 6, 3.5),
                new CPU("AMD Ryzen 5 5600", "AM4", 6, 3.6),
                new CPU("AMD Ryzen 7 5800X", "AM4", 8, 3.8)
        });

        comboMotherboard = new JComboBox<>(new Motherboard[]{
                new Motherboard("ASUS Prime B660M", "LGA1700", "B660", 64, "DDR4"),
                new Motherboard("MSI PRO B760M", "LGA1700", "B760", 128, "DDR4"),
                new Motherboard("ASUS TUF B550M", "AM4", "B550", 128, "DDR4")
        });

        comboRAM = new JComboBox<>(new RAM[]{
                new RAM("Team Elite 8GB 3200", 8, 3200, "DDR4"),
                new RAM("Corsair Vengeance 16GB 3200", 16, 3200, "DDR4"),
                new RAM("G.Skill Ripjaws 32GB 3600", 32, 3600, "DDR4")
        });

        comboGPU = new JComboBox<>(new GPU[]{
                new GPU("NVIDIA GTX 1650 4GB", 4),
                new GPU("NVIDIA RTX 3050 8GB", 8),
                new GPU("NVIDIA RTX 4060 8GB", 8),
                new GPU("AMD RX 6600 8GB", 8)
        });

        comboStorage = new JComboBox<>(new Storage[]{
                new Storage("SSD SATA 240GB", 240, "SSD"),
                new Storage("SSD NVMe 512GB", 512, "SSD"),
                new Storage("HDD 1TB 7200rpm", 1000, "HDD")
        });

        comboPSU = new JComboBox<>(new PowerSupply[]{
                new PowerSupply("PSU 450W Bronze", 450),
                new PowerSupply("PSU 550W Bronze", 550),
                new PowerSupply("PSU 650W Gold", 650)
        });

        applySimpleRenderer(comboCPU);
        applySimpleRenderer(comboMotherboard);
        applySimpleRenderer(comboRAM);
        applySimpleRenderer(comboGPU);
        applySimpleRenderer(comboStorage);
        applySimpleRenderer(comboPSU);

        Dimension comboSize = new Dimension(280, 28);
        comboCPU.setPreferredSize(comboSize);
        comboMotherboard.setPreferredSize(comboSize);
        comboRAM.setPreferredSize(comboSize);
        comboGPU.setPreferredSize(comboSize);
        comboStorage.setPreferredSize(comboSize);
        comboPSU.setPreferredSize(comboSize);

        int row = 0;

        // CPU
        gbc.gridx = 0; gbc.gridy = row;
        formPanel.add(new JLabel("CPU:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboCPU, gbc);
        row++;

        // Motherboard
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        formPanel.add(new JLabel("Motherboard:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboMotherboard, gbc);
        row++;

        // RAM
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        formPanel.add(new JLabel("RAM:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboRAM, gbc);
        row++;

        // GPU
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        formPanel.add(new JLabel("GPU:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboGPU, gbc);
        row++;

        // Storage
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        formPanel.add(new JLabel("Storage:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboStorage, gbc);
        row++;

        // PSU
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0;
        formPanel.add(new JLabel("Power Supply:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        formPanel.add(comboPSU, gbc);
        row++;

        // Ringkasan
        areaPreview = new JTextArea(12, 30);
        areaPreview.setEditable(false);
        areaPreview.setLineWrap(true);
        areaPreview.setWrapStyleWord(true);
        areaPreview.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane scrollPreview = new JScrollPane(areaPreview);
        scrollPreview.setBorder(
                BorderFactory.createTitledBorder("Ringkasan Konfigurasi")
        );

        // Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        JButton btnPreview = new JButton("Preview Build");
        JButton btnCek = new JButton("Cek Kompatibilitas");
        JButton btnPesan = new JButton("Buat Pesanan");

        buttonPanel.add(btnPreview);
        buttonPanel.add(btnCek);
        buttonPanel.add(btnPesan);

        // Panel kiri (form + tombol)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setOpaque(false);
        leftPanel.add(formPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Split kiri–kanan
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                leftPanel,
                scrollPreview
        );
        splitPane.setResizeWeight(0.6);
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);

        // Event Listener

        // Preview build
        btnPreview.addActionListener(e -> {
            updatePCFromSelection();
            updatePreviewArea();
        });

        // Cek kompatibilitas
        btnCek.addActionListener(e -> {
            try {
                updatePCFromSelection();
                builderController.cekKompatibilitas();
                int estimasiDaya = estimateWatt(builderController.getPC());
                JOptionPane.showMessageDialog(
                        this,
                        "Semua komponen kompatibel.\nPerkiraan kebutuhan daya: " + estimasiDaya + " W",
                        "Kompatibilitas OK",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IncompatibleComponentException ex) {
                String msg = ex.getMessage();
                if (msg != null && msg.toLowerCase().startsWith("incompatible:")) {
                    msg = msg.substring("incompatible:".length()).trim();
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Komponen tidak kompatibel: " + msg,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Terjadi kesalahan: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });

        // Buat pesanan
        btnPesan.addActionListener(e -> {
            try {
                updatePCFromSelection();
                builderController.cekKompatibilitas();

                PC pc = builderController.getPC();
                Pesanan pesanan = new Pesanan(pc, StatusPesanan.MENUNGGU_KOMPONEN);
                orderManager.tambahPesanan(pesanan);

                JOptionPane.showMessageDialog(
                        this,
                        "Pesanan #" + pesanan.getId() + " berhasil dibuat.\n" +
                                "Status awal: " + pesanan.getStatus(),
                        "Pesanan dibuat",
                        JOptionPane.INFORMATION_MESSAGE
                );

                builderController.resetBuild();
                updatePreviewArea();

            } catch (IncompatibleComponentException ex) {
                String msg = ex.getMessage();
                if (msg != null && msg.toLowerCase().startsWith("incompatible:")) {
                    msg = msg.substring("incompatible:".length()).trim();
                }

                JOptionPane.showMessageDialog(
                        this,
                        "Komponen tidak kompatibel: " + msg,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Terjadi kesalahan: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        });

        // tampilkan preview awal
        updatePCFromSelection();
        updatePreviewArea();
    }

    // Fungsi Bantu (Helper)
    private void updatePCFromSelection() {
        builderController.resetBuild();

        CPU cpu = (CPU) comboCPU.getSelectedItem();
        Motherboard mb = (Motherboard) comboMotherboard.getSelectedItem();
        RAM ram = (RAM) comboRAM.getSelectedItem();
        GPU gpu = (GPU) comboGPU.getSelectedItem();
        Storage storage = (Storage) comboStorage.getSelectedItem();
        PowerSupply psu = (PowerSupply) comboPSU.getSelectedItem();

        if (cpu != null) builderController.pasangCPU(cpu);
        if (mb != null) builderController.pasangMotherboard(mb);
        if (ram != null) builderController.pasangRAM(ram);
        if (gpu != null) builderController.pasangGPU(gpu);
        if (storage != null) builderController.pasangStorage(storage);
        if (psu != null) builderController.pasangPSU(psu);
    }

    private String formatKomponen(String label, Komponen k) {
        if (k == null) return "-";
        String s = k.toString();
        String prefix = label.trim() + ":";
        if (s.startsWith(prefix)) {
            s = s.substring(prefix.length()).trim();
        }
        return s;
    }

    private <T extends Komponen> void applySimpleRenderer(JComboBox<T> combo) {
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list,
                    Object value,
                    int index,
                    boolean isSelected,
                    boolean cellHasFocus) {

                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Komponen) {
                    Komponen k = (Komponen) value;
                    // hanya tampilkan nama komponen saja
                    setText(k.getNama());
                }

                return this;
            }
        });
    }

    private void updatePreviewArea() {
        PC pc = builderController.getPC();
        StringBuilder sb = new StringBuilder();
        sb.append("Konfigurasi PC Saat Ini:\n");
        sb.append("------------------------\n");

        sb.append("CPU         : ")
                .append(formatKomponen("CPU", pc.getCpu())).append("\n");
        sb.append("Motherboard : ")
                .append(formatKomponen("Motherboard", pc.getMotherboard())).append("\n");
        sb.append("RAM         : ")
                .append(formatKomponen("RAM", pc.getRam())).append("\n");
        sb.append("GPU         : ")
                .append(formatKomponen("GPU", pc.getGpu())).append("\n");
        sb.append("Storage     : ")
                .append(formatKomponen("Storage", pc.getStorage())).append("\n");
        sb.append("PowerSupply : ")
                .append(formatKomponen("PowerSupply", pc.getPowerSupply())).append("\n");

        try {
            int estimasiDaya = estimateWatt(pc);
            sb.append("\nPerkiraan kebutuhan daya minimum PSU : ")
                    .append(estimasiDaya).append(" W");
        } catch (Exception ignore) {}

        areaPreview.setText(sb.toString());
    }

    private int estimateWatt(PC pc) {
        if (pc == null) return 0;

        int total = 0;

        if (pc.getCpu() != null) {
            int cores = Math.max(1, pc.getCpu().getCoreCount());
            total += cores * 10;
        }

        if (pc.getGpu() != null) {
            int assumed = 150;
            try {
                int vram = Math.max(0, pc.getGpu().getVram() - 4);
                assumed += vram * 15;
            } catch (Exception e) { }
            total += assumed;
        }

        if (pc.getRam() != null) {
            total += Math.max(1, pc.getRam().getKapasitas()) * 3;
        }

        if (pc.getStorage() != null) {
            String tipe = pc.getStorage().getTipe();
            if (tipe != null && tipe.equalsIgnoreCase("HDD")) total += 8;
            else total += 5;
        }

        if (pc.getMotherboard() != null) {
            total += 30;
        }

        total = (int) Math.ceil(total * 1.2);
        return Math.max(50, total);
    }
}
