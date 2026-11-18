package com.campus.energy.ui;

import com.campus.energy.dao.FaultDAO;
import com.campus.energy.model.FaultReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class FaultPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtBuildingId, txtDesc, txtDate;
    private JComboBox<String> cmbStatus;
    private FaultDAO faultDAO;

    public FaultPanel() {
        faultDAO = new FaultDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Fault ID", "Building ID", "Description", "Reported Date", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtBuildingId = new JTextField(5);
        txtDesc = new JTextField(12);
        txtDate = new JTextField(9);
        cmbStatus = new JComboBox<>(new String[]{"Pending", "In Progress", "Resolved"});
        JButton btnAdd = new JButton("Report Fault");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Bldg ID:"));
        formPanel.add(txtBuildingId);
        formPanel.add(new JLabel("Desc:"));
        formPanel.add(txtDesc);
        formPanel.add(new JLabel("Date:"));
        formPanel.add(txtDate);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(cmbStatus);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                faultDAO.addFault(
                    Integer.parseInt(txtBuildingId.getText()),
                    txtDesc.getText(),
                    Date.valueOf(txtDate.getText()),
                    (String) cmbStatus.getSelectedItem()
                );
                loadData();
                txtBuildingId.setText("");
                txtDesc.setText("");
                txtDate.setText("");
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
            List<FaultReport> list = faultDAO.getAllFaults();
            for (FaultReport f : list) {
                model.addRow(new Object[]{f.getFaultId(), f.getBuildingId(), f.getDescription(), f.getReportedDate(), f.getStatus()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
