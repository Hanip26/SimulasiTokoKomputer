/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.Map;

import inventory.Inventaris;

public class TabelInventarisPanel extends JPanel {

    private Inventaris inventaris;
    private JTable table;
    private DefaultTableModel tableModel;

    public TabelInventarisPanel(Inventaris inventaris) {
        this.inventaris = inventaris;
        initComponents();
        reloadData();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"Nama Komponen", "Stok"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void reloadData() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, Integer> entry : inventaris.getStokMap().entrySet()) {
            tableModel.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }
    }
}
