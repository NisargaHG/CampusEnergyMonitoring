package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.EnergyMeter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeterDAO {
    public List<EnergyMeter> getAllMeters() throws SQLException {
        List<EnergyMeter> list = new ArrayList<>();
        String sql = "SELECT * FROM ENERGY_METER";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new EnergyMeter(rs.getInt("Meter_ID"), rs.getInt("Building_ID"), rs.getString("Meter_Type")));
            }
        }
        return list;
    }

    public void addMeter(int buildingId, String meterType) throws SQLException {
        String sql = "INSERT INTO ENERGY_METER (Building_ID, Meter_Type) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, buildingId);
            pstmt.setString(2, meterType);
            pstmt.executeUpdate();
        }
    }
}
