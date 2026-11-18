package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.EnergyConsumption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsumptionDAO {
    public List<EnergyConsumption> getAllConsumption() throws SQLException {
        List<EnergyConsumption> list = new ArrayList<>();
        String sql = "SELECT * FROM ENERGY_CONSUMPTION";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EnergyConsumption(rs.getInt("Consumption_ID"), rs.getInt("Meter_ID"), rs.getDate("Date"), rs.getDouble("Units_Consumed")));
            }
        }
        return list;
    }

    public void addConsumption(int meterId, Date date, double units) throws SQLException {
        String sql = "INSERT INTO ENERGY_CONSUMPTION (Meter_ID, Date, Units_Consumed) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, meterId);
            pstmt.setDate(2, date);
            pstmt.setDouble(3, units);
            pstmt.executeUpdate();
        }
    }
}
