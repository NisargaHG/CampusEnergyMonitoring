package com.campus.energy.ui;

import com.campus.energy.dao.ConsumptionDAO;
import com.campus.energy.model.EnergyConsumption;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class ConsumptionPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMeterId, txtDate, txtUnits;
    private ConsumptionDAO consumptionDAO;

    public ConsumptionPanel() {
        consumptionDAO = new ConsumptionDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Consumption ID", "Meter ID", "Date", "Units Consumed"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtMeterId = new JTextField(6);
        txtDate = new JTextField(10);
        txtUnits = new JTextField(8);
        JButton btnAdd = new JButton("Add Record");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Meter ID:"));
        formPanel.add(txtMeterId);
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        formPanel.add(txtDate);
        formPanel.add(new JLabel("Units:"));
        formPanel.add(txtUnits);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                consumptionDAO.addConsumption(
                    Integer.parseInt(txtMeterId.getText()),
                    Date.valueOf(txtDate.getText()),
                    Double.parseDouble(txtUnits.getText())
                );
                loadData();
                txtMeterId.setText("");
                txtDate.setText("");
                txtUnits.setText("");
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
            List<EnergyConsumption> list = consumptionDAO.getAllConsumption();
            for (EnergyConsumption c : list) {
                model.addRow(new Object[]{c.getConsumptionId(), c.getMeterId(), c.getDate(), c.getUnitsConsumed()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
