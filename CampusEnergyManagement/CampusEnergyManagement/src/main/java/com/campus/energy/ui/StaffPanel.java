package com.campus.energy.ui;

import com.campus.energy.dao.StaffDAO;
import com.campus.energy.model.MaintenanceStaff;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StaffPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtName, txtPhone, txtSpec;
    private StaffDAO staffDAO;

    public StaffPanel() {
        staffDAO = new StaffDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Staff ID", "Name", "Phone", "Specialization"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtName = new JTextField(12);
        txtPhone = new JTextField(10);
        txtSpec = new JTextField(12);
        JButton btnAdd = new JButton("Add Staff");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(txtPhone);
        formPanel.add(new JLabel("Specialization:"));
        formPanel.add(txtSpec);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                staffDAO.addStaff(txtName.getText(), txtPhone.getText(), txtSpec.getText());
                loadData();
                txtName.setText("");
                txtPhone.setText("");
                txtSpec.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnRefresh.addActionListener(e -> loadData());
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            List<MaintenanceStaff> list = staffDAO.getAllStaff();
            for (MaintenanceStaff s : list) {
                model.addRow(new Object[]{s.getStaffId(), s.getStaffName(), s.getPhone(), s.getSpecialization()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
