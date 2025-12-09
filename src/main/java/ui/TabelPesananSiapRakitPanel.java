/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;
import model.Pesanan;
import order.OrderManager;

public class TabelPesananSiapRakitPanel extends JPanel {

    private OrderManager orderManager;
    private JTable table;
    private DefaultTableModel tableModel;

    private static final Color HEADER_BG = new Color(34, 197, 94);
    private static final Color ROW_EVEN = new Color(240, 253, 244);
    private static final Color ROW_ODD = Color.WHITE;

    public TabelPesananSiapRakitPanel(OrderManager orderManager) {
        this.orderManager = orderManager;
        initComponents();
        reloadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(false);

        String[] columnNames = {"Order ID", "Status", "Components"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        styleTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void styleTable() {
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setGridColor(new Color(220, 252, 231));
        table.setIntercellSpacing(new Dimension(1, 1));
        table.setSelectionBackground(new Color(220, 252, 231));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("SansSerif", Font.BOLD, 13));
        header.setBackground(HEADER_BG);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(HEADER_BG);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("SansSerif", Font.BOLD, 13));
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    setBackground(row % 2 == 0 ? ROW_EVEN : ROW_ODD);
                }

                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                setHorizontalAlignment(CENTER);

                if (column == 0) {
                    setFont(new Font("SansSerif", Font.BOLD, 14));
                    setForeground(new Color(30, 41, 59));
                } else if (column == 1) {
                    setFont(new Font("SansSerif", Font.PLAIN, 12));
                    setForeground(new Color(22, 163, 74));
                } else {
                    setFont(new Font("SansSerif", Font.PLAIN, 11));
                    setForeground(new Color(100, 116, 139));
                    setHorizontalAlignment(LEFT);
                }

                return this;
            }
        };

        table.setDefaultRenderer(Object.class, cellRenderer);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(180);
        columnModel.getColumn(2).setPreferredWidth(220);
    }

    public void reloadData() {
        tableModel.setRowCount(0);
        List<Pesanan> siap = orderManager.getPesananSiapDirakit();

        if (siap.isEmpty()) {
            tableModel.addRow(new Object[]{"---", "No ready orders", "---"});
        } else {
            for (Pesanan p : siap) {
                String statusText = p.getStatus().name().replace("_", " ");
                int jumlahKomponen = p.getPC().getKomponen().size();
                tableModel.addRow(new Object[]{
                        "Order #" + p.getId(),
                        statusText,
                        jumlahKomponen + " components"
                });
            }
        }
    }
}