package com.campus.energy.ui;

import com.campus.energy.dao.BuildingDAO;
import com.campus.energy.model.Building;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BuildingPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtName, txtLocation;
    private BuildingDAO buildingDAO;

    public BuildingPanel() {
        buildingDAO = new BuildingDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"ID", "Building Name", "Location"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtName = new JTextField(15);
        txtLocation = new JTextField(15);
        JButton btnAdd = new JButton("Add Building");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Name:"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Location:"));
        formPanel.add(txtLocation);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                buildingDAO.addBuilding(txtName.getText(), txtLocation.getText());
                loadData();
                txtName.setText("");
                txtLocation.setText("");
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
            List<Building> list = buildingDAO.getAllBuildings();
            for (Building b : list) {
                model.addRow(new Object[]{b.getBuildingId(), b.getBuildingName(), b.getLocation()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
