package com.campus.energy.ui;

import com.campus.energy.dao.AnalyticsDAO;
import com.campus.energy.model.EnergyAnalytics;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AnalyticsPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private AnalyticsDAO analyticsDAO;

    public AnalyticsPanel() {
        analyticsDAO = new AnalyticsDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Analytics ID", "Building ID", "Month/Year", "Total Consumption (kWh)", "Avg Daily Usage"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefresh = new JButton("Refresh Analytics");
        formPanel.add(btnRefresh);
        add(formPanel, BorderLayout.SOUTH);

        btnRefresh.addActionListener(e -> loadData());
        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try {
            List<EnergyAnalytics> list = analyticsDAO.getAllAnalytics();
            for (EnergyAnalytics a : list) {
                model.addRow(new Object[]{a.getAnalyticsId(), a.getBuildingId(), a.getMonthYear(), a.getTotalConsumption(), a.getAverageDailyUsage()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
