/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
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

    private JPanel previewPanel;
    private JLabel lblEstimatedWatt;
    private JProgressBar progressBar;

    private static final Color PRIMARY_COLOR = new Color(37, 99, 235);
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color WARNING_COLOR = new Color(251, 146, 60);
    private static final Color DANGER_COLOR = new Color(239, 68, 68);
    private static final Color BG_COLOR = new Color(248, 250, 252);

    public PCConfiguratorPanel(OrderManager orderManager) {
        this.orderManager = orderManager;
        this.builderController = new PCBuilderController();
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(15, 15));
        setOpaque(false);

        // Form Panel dengan styling modern
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setOpaque(false);

        comboCPU = createStyledCombo(new CPU[]{
                new CPU("Intel Core i3-12100F", "LGA1700", 4, 3.3),
                new CPU("Intel Core i5-12400F", "LGA1700", 6, 3.5),
                new CPU("AMD Ryzen 5 5600", "AM4", 6, 3.6),
                new CPU("AMD Ryzen 7 5800X", "AM4", 8, 3.8)
        });

        comboMotherboard = createStyledCombo(new Motherboard[]{
                new Motherboard("ASUS Prime B660M", "LGA1700", "B660", 64, "DDR4"),
                new Motherboard("MSI PRO B760M", "LGA1700", "B760", 128, "DDR4"),
                new Motherboard("ASUS TUF B550M", "AM4", "B550", 128, "DDR4")
        });

        comboRAM = createStyledCombo(new RAM[]{
                new RAM("Team Elite 8GB 3200", 8, 3200, "DDR4"),
                new RAM("Corsair Vengeance 16GB 3200", 16, 3200, "DDR4"),
                new RAM("G.Skill Ripjaws 32GB 3600", 32, 3600, "DDR4")
        });

        comboGPU = createStyledCombo(new GPU[]{
                new GPU("NVIDIA GTX 1650 4GB", 4),
                new GPU("NVIDIA RTX 3050 8GB", 8),
                new GPU("NVIDIA RTX 4060 8GB", 8),
                new GPU("AMD RX 6600 8GB", 8)
        });

        comboStorage = createStyledCombo(new Storage[]{
                new Storage("SSD SATA 240GB", 240, "SSD"),
                new Storage("SSD NVMe 512GB", 512, "SSD"),
                new Storage("HDD 1TB 7200rpm", 1000, "HDD")
        });

        comboPSU = createStyledCombo(new PowerSupply[]{
                new PowerSupply("PSU 450W Bronze", 450),
                new PowerSupply("PSU 550W Bronze", 550),
                new PowerSupply("PSU 650W Gold", 650)
        });

        formPanel.add(createComponentRow("CPU", comboCPU));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createComponentRow("Motherboard", comboMotherboard));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createComponentRow("RAM", comboRAM));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createComponentRow("GPU", comboGPU));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createComponentRow("Storage", comboStorage));
        formPanel.add(Box.createVerticalStrut(12));
        formPanel.add(createComponentRow("Power Supply", comboPSU));
        formPanel.add(Box.createVerticalStrut(20));

        // Action buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        JButton btnCheck = createActionButton("Check", PRIMARY_COLOR);
        JButton btnPreview = createActionButton("Preview", new Color(99, 102, 241));
        JButton btnOrder = createActionButton("Order", SUCCESS_COLOR);

        buttonPanel.add(btnCheck);
        buttonPanel.add(btnPreview);
        buttonPanel.add(btnOrder);

        formPanel.add(buttonPanel);

        // Preview panel dengan card design
        previewPanel = createPreviewPanel();

        // Scroll pane untuk form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Add to main panel
        add(scrollPane, BorderLayout.CENTER);
        add(previewPanel, BorderLayout.SOUTH);

        // Event listeners
        btnCheck.addActionListener(e -> checkCompatibility());
        btnPreview.addActionListener(e -> showPreviewDialog());
        btnOrder.addActionListener(e -> createOrder());

        // Auto-update preview on selection change
        comboCPU.addActionListener(e -> updatePreview());
        comboMotherboard.addActionListener(e -> updatePreview());
        comboRAM.addActionListener(e -> updatePreview());
        comboGPU.addActionListener(e -> updatePreview());
        comboStorage.addActionListener(e -> updatePreview());
        comboPSU.addActionListener(e -> updatePreview());

        updatePreview();
    }

    private <T extends Komponen> JComboBox<T> createStyledCombo(T[] items) {
        JComboBox<T> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        combo.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel label = new JLabel(value != null ? value.getNama() : "");
            label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            if (isSelected) {
                label.setBackground(PRIMARY_COLOR);
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
            }
            return label;
        });
        return combo;
    }

    JPanel createComponentRow(String label, JComponent component) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        row.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0)); // padding kiri kecil (opsional)

        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(new Color(51, 65, 85));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        component.setAlignmentX(Component.LEFT_ALIGNMENT);

        row.add(lbl);
        row.add(Box.createVerticalStrut(6));
        row.add(component);

        return row;
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

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
        button.setPreferredSize(new Dimension(0, 45));

        return button;
    }

    private JPanel createPreviewPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Gradient background
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(241, 245, 249),
                        0, getHeight(), new Color(226, 232, 240)
                );
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setPreferredSize(new Dimension(0, 120));

        JLabel titleLabel = new JLabel("Power Estimation");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(new Color(30, 41, 59));

        lblEstimatedWatt = new JLabel("Calculating...");
        lblEstimatedWatt.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblEstimatedWatt.setForeground(PRIMARY_COLOR);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Segoe UI", Font.BOLD, 11));
        progressBar.setPreferredSize(new Dimension(0, 25));
        progressBar.setForeground(SUCCESS_COLOR);
        progressBar.setBackground(new Color(226, 232, 240));
        progressBar.setBorder(BorderFactory.createEmptyBorder());

        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel);
        contentPanel.add(lblEstimatedWatt);
        contentPanel.add(progressBar);

        panel.add(contentPanel, BorderLayout.CENTER);

        return panel;
    }

    private void updatePreview() {
        updatePCFromSelection();
        PC pc = builderController.getPC();
        int estimatedWatt = estimateWatt(pc);

        lblEstimatedWatt.setText(estimatedWatt + "W Required");

        PowerSupply psu = pc.getPowerSupply();
        if (psu != null) {
            int percentage = (int) ((estimatedWatt / (double) psu.getDaya()) * 100);
            progressBar.setValue(Math.min(percentage, 100));
            progressBar.setString(percentage + "% PSU Usage");

            if (percentage > 90) {
                progressBar.setForeground(DANGER_COLOR);
            } else if (percentage > 70) {
                progressBar.setForeground(WARNING_COLOR);
            } else {
                progressBar.setForeground(SUCCESS_COLOR);
            }
        } else {
            progressBar.setValue(0);
            progressBar.setString("Select PSU");
        }
    }

    private void showPreviewDialog() {
        updatePCFromSelection();
        PC pc = builderController.getPC();
        updatePreview();

        String cpuName      = pc.getCpu()         != null ? pc.getCpu().getNama()         : "-";
        String moboName     = pc.getMotherboard() != null ? pc.getMotherboard().getNama() : "-";
        String ramName      = pc.getRam()         != null ? pc.getRam().getNama()         : "-";
        String gpuName      = pc.getGpu()         != null ? pc.getGpu().getNama()         : "-";
        String storageName  = pc.getStorage()     != null ? pc.getStorage().getNama()     : "-";
        String psuName      = pc.getPowerSupply() != null ? pc.getPowerSupply().getNama() : "-";

        int estimatedWatt = estimateWatt(pc);

        String html =
                "<html><body style='font-family:Segoe UI; font-size:11px;'>" +
                        "<b>Ringkasan Konfigurasi PC:</b><br><br>" +
                        "<table cellspacing='0' cellpadding='2'>" +
                        "<tr><td>CPU</td><td style='padding:0 6px;'>:</td><td>" + cpuName + "</td></tr>" +
                        "<tr><td>Motherboard</td><td style='padding:0 6px;'>:</td><td>" + moboName + "</td></tr>" +
                        "<tr><td>RAM</td><td style='padding:0 6px;'>:</td><td>" + ramName + "</td></tr>" +
                        "<tr><td>GPU</td><td style='padding:0 6px;'>:</td><td>" + gpuName + "</td></tr>" +
                        "<tr><td>Storage</td><td style='padding:0 6px;'>:</td><td>" + storageName + "</td></tr>" +
                        "<tr><td>Power Supply</td><td style='padding:0 6px;'>:</td><td>" + psuName + "</td></tr>" +
                        "</table><br>" +
                        "<b>Perkiraan Daya: " + estimatedWatt + " W</b>" +
                        "</body></html>";

        JLabel label = new JLabel(html);

        JOptionPane.showMessageDialog(
                this,
                label,
                "Preview PC Build",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void checkCompatibility() {
        try {
            updatePCFromSelection();
            builderController.cekKompatibilitas();
            int estimasiDaya = estimateWatt(builderController.getPC());

            showCustomDialog(
                    "Compatibility Check",
                    "All components are compatible!\nEstimated power requirement: " + estimasiDaya + " W",
                    SUCCESS_COLOR
            );
        } catch (IncompatibleComponentException ex) {
            String msg = ex.getMessage();
            if (msg != null && msg.toLowerCase().startsWith("incompatible:")) {
                msg = msg.substring("incompatible:".length()).trim();
            }
            showCustomDialog("Incompatibility Detected", msg, DANGER_COLOR);
        } catch (Exception ex) {
            showCustomDialog("Error", "An error occurred: " + ex.getMessage(), WARNING_COLOR);
        }
    }

    private void createOrder() {
        try {
            updatePCFromSelection();
            builderController.cekKompatibilitas();

            PC pc = builderController.getPC();
            Pesanan pesanan = new Pesanan(pc, StatusPesanan.MENUNGGU_KOMPONEN);
            orderManager.tambahPesanan(pesanan);

            showCustomDialog(
                    "Order Created",
                    "Order #" + pesanan.getId() + " has been created successfully!\nStatus: " + pesanan.getStatus(),
                    SUCCESS_COLOR
            );

            builderController.resetBuild();
            updatePreview();

        } catch (IncompatibleComponentException ex) {
            String msg = ex.getMessage();
            if (msg != null && msg.toLowerCase().startsWith("incompatible:")) {
                msg = msg.substring("incompatible:".length()).trim();
            }
            showCustomDialog("Order Failed", "Incompatible components: " + msg, DANGER_COLOR);
        }
    }

    private void showCustomDialog(String title, String message, Color color) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), title, true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 3),
                BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(color);

        JTextArea messageArea = new JTextArea(message);
        messageArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        messageArea.setEditable(false);
        messageArea.setOpaque(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);

        JButton btnOk = createActionButton("OK", color);
        btnOk.setPreferredSize(new Dimension(100, 35));
        btnOk.addActionListener(e -> dialog.dispose());

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.add(btnOk);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(messageArea, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        dialog.add(mainPanel);
        dialog.pack();
        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

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