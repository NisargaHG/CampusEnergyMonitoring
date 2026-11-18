package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.MaintenanceStaff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    public List<MaintenanceStaff> getAllStaff() throws SQLException {
        List<MaintenanceStaff> list = new ArrayList<>();
        String sql = "SELECT * FROM MAINTENANCE_STAFF";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new MaintenanceStaff(rs.getInt("Staff_ID"), rs.getString("Staff_Name"), rs.getString("Phone"), rs.getString("Specialization")));
            }
        }
        return list;
    }

    public void addStaff(String name, String phone, String specialization) throws SQLException {
        String sql = "INSERT INTO MAINTENANCE_STAFF (Staff_Name, Phone, Specialization) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, specialization);
            pstmt.executeUpdate();
        }
    }
}
