package com.campus.energy.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Campus Electrical Energy Monitoring & Fault Management System");
        setSize(1050, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Buildings", new BuildingPanel());
        tabbedPane.addTab("Energy Meters", new MeterPanel());
        tabbedPane.addTab("Consumption", new ConsumptionPanel());
        tabbedPane.addTab("Fault Reports", new FaultPanel());
        tabbedPane.addTab("Maintenance Staff", new StaffPanel());
        tabbedPane.addTab("Repair Tasks", new TaskPanel());
        tabbedPane.addTab("Analytics", new AnalyticsPanel());

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new MainFrame().setVisible(true);
        });
    }
}
