/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;
import inventory.Inventaris;

public class TabelInventarisPanel extends JPanel {

    private Inventaris inventaris;
    private JTable table;
    private DefaultTableModel tableModel;

    private static final Color HEADER_BG = new Color(37, 99, 235);
    private static final Color ROW_EVEN = new Color(248, 250, 252);
    private static final Color ROW_ODD = Color.WHITE;
    private static final Color SUCCESS_COLOR = new Color(34, 197, 94);
    private static final Color WARNING_COLOR = new Color(251, 146, 60);
    private static final Color DANGER_COLOR = new Color(239, 68, 68);

    public TabelInventarisPanel(Inventaris inventaris) {
        this.inventaris = inventaris;
        initComponents();
        reloadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setOpaque(false);

        String[] columnNames = {"Component Name", "Stock", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return Integer.class;
                return String.class;
            }
        };

        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(false);
        styleTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(203, 213, 225), 1));
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void styleTable() {
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(35);

        // garis tabel lebih tegas
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(true);
        table.setGridColor(new Color(209, 213, 219));              // sedikit lebih gelap
        table.setIntercellSpacing(new Dimension(1, 1));

        table.setSelectionBackground(new Color(219, 234, 254));
        table.setSelectionForeground(new Color(30, 41, 59));

        // Header
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
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);       // ⬅ header di tengah

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        // Cell renderer
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {

                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    setBackground(row % 2 == 0 ? ROW_EVEN : ROW_ODD);
                    setForeground(new Color(30, 41, 59));
                }

                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                if (column == 0) {
                    setHorizontalAlignment(LEFT);
                    setFont(new Font("SansSerif", Font.PLAIN, 13));
                } else if (column == 1) {
                    setHorizontalAlignment(CENTER);
                    setFont(new Font("SansSerif", Font.BOLD, 14));
                    if (value instanceof Integer) {
                        int stock = (Integer) value;
                        if (!isSelected) {
                            if (stock == 0) {
                                setForeground(DANGER_COLOR);
                            } else if (stock < 5) {
                                setForeground(WARNING_COLOR);
                            } else {
                                setForeground(SUCCESS_COLOR);
                            }
                        }
                    }
                } else if (column == 2) {
                    setHorizontalAlignment(CENTER);
                    setFont(new Font("SansSerif", Font.BOLD, 11));
                    if (!isSelected && value != null) {
                        String status = value.toString();
                        if (status.contains("Out")) {
                            setForeground(DANGER_COLOR);
                        } else if (status.contains("Low")) {
                            setForeground(WARNING_COLOR);
                        } else {
                            setForeground(SUCCESS_COLOR);
                        }
                    }
                }

                return this;
            }
        };

        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setDefaultRenderer(Integer.class, centerRenderer);
        table.setDefaultRenderer(String.class, centerRenderer);

        // Column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(300);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(120);
    }

    public void reloadData() {
        tableModel.setRowCount(0);

        Map<String, Integer> stokMap = new TreeMap<>(inventaris.getStokMap());

        for (Map.Entry<String, Integer> entry : stokMap.entrySet()) {
            String nama = entry.getKey();
            Integer stock = entry.getValue();
            String status;

            if (stock == 0) {
                status = "● Out of Stock";
            } else if (stock < 5) {
                status = "● Low Stock";
            } else {
                status = "● In Stock";
            }

            tableModel.addRow(new Object[]{nama, stock, status});
        }
    }
}