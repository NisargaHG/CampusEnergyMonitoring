package com.campus.energy.ui;

import com.campus.energy.dao.TaskDAO;
import com.campus.energy.model.RepairTask;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TaskPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtFaultId, txtStaffId, txtDate;
    private JComboBox<String> cmbStatus;
    private TaskDAO taskDAO;

    public TaskPanel() {
        taskDAO = new TaskDAO();
        setLayout(new BorderLayout());

        model = new DefaultTableModel(new String[]{"Task ID", "Fault ID", "Staff ID", "Assignment Date", "Resolution Date", "Status"}, 0);
        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtFaultId = new JTextField(5);
        txtStaffId = new JTextField(5);
        txtDate = new JTextField(9);
        cmbStatus = new JComboBox<>(new String[]{"Assigned", "In Progress", "Completed"});
        JButton btnAdd = new JButton("Assign Task");
        JButton btnRefresh = new JButton("Refresh");

        formPanel.add(new JLabel("Fault ID:"));
        formPanel.add(txtFaultId);
        formPanel.add(new JLabel("Staff ID:"));
        formPanel.add(txtStaffId);
        formPanel.add(new JLabel("Date:"));
        formPanel.add(txtDate);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(cmbStatus);
        formPanel.add(btnAdd);
        formPanel.add(btnRefresh);

        add(formPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> {
            try {
                taskDAO.addTask(
                    Integer.parseInt(txtFaultId.getText()),
                    Integer.parseInt(txtStaffId.getText()),
                    Date.valueOf(txtDate.getText()),
                    (String) cmbStatus.getSelectedItem()
                );
                loadData();
                txtFaultId.setText("");
                txtStaffId.setText("");
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
            List<RepairTask> list = taskDAO.getAllTasks();
            for (RepairTask t : list) {
                model.addRow(new Object[]{t.getTaskId(), t.getFaultId(), t.getStaffId(), t.getAssignmentDate(), t.getResolutionDate(), t.getRepairStatus()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + ex.getMessage());
        }
    }
}
