package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.EnergyAnalytics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnalyticsDAO {
    public List<EnergyAnalytics> getAllAnalytics() throws SQLException {
        List<EnergyAnalytics> list = new ArrayList<>();
        String sql = "SELECT * FROM ENERGY_ANALYTICS";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EnergyAnalytics(rs.getInt("Analytics_ID"), rs.getInt("Building_ID"), rs.getString("Month_Year"), rs.getDouble("Total_Consumption"), rs.getDouble("Average_Daily_Usage")));
            }
        }
        return list;
    }
}
