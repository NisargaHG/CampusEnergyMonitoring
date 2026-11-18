package com.campus.energy.ui;

import com.campus.energy.dao.MeterDAO;
import com.campus.energy.model.EnergyMeter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MeterPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtBuildingId, txtMeterType;
    private MeterDAO meterDAO;

    public MeterPanel() {
        meterDAO = new MeterDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Meter ID", "Building ID", "Meter Type"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuildingId = new JTextField(8);
        txtMeterType = new JTextField(15);
        JButton btnAdd = new JButton("Add Meter");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Building ID:"));
        formPanel.add(txtBuildingId);
        formPanel.add(new JLabel("Meter Type:"));
        formPanel.add(txtMeterType);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                meterDAO.addMeter(Integer.parseInt(txtBuildingId.getText()), txtMeterType.getText());
                loadData();
                txtBuildingId.setText("");
                txtMeterType.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnRefresh.addActionListener(e -> loadData());
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            List<EnergyMeter> list = meterDAO.getAllMeters();
            for (EnergyMeter m : list) {
                model.addRow(new Object[]{m.getMeterId(), m.getBuildingId(), m.getMeterType()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
