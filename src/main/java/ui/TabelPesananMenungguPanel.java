/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import model.Pesanan;
import order.OrderManager;
import order.StatusPesanan;

public class TabelPesananMenungguPanel extends JPanel {

    private OrderManager orderManager;
    private JTable table;
    private DefaultTableModel tableModel;

    public TabelPesananMenungguPanel(OrderManager orderManager) {
        this.orderManager = orderManager;
        initComponents();
        reloadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"ID Pesanan", "Status"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setBorder(BorderFactory.createTitledBorder("Pesanan Menunggu Komponen"));
    }

    public void reloadData() {
        tableModel.setRowCount(0);
        List<Pesanan> menunggu = orderManager.getPesananMenunggu();
        for (Pesanan p : menunggu) {
            tableModel.addRow(new Object[]{
                    p.getId(),
                    p.getStatus().name() // enum -> String
            });
        }
    }
}
