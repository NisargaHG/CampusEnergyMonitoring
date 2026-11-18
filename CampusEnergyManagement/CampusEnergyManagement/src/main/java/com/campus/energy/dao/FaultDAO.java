package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.FaultReport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaultDAO {
    public List<FaultReport> getAllFaults() throws SQLException {
        List<FaultReport> list = new ArrayList<>();
        String sql = "SELECT * FROM FAULT_REPORT";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new FaultReport(rs.getInt("Fault_ID"), rs.getInt("Building_ID"), rs.getString("Description"), rs.getDate("Reported_Date"), rs.getString("Status")));
            }
        }
        return list;
    }

    public void addFault(int buildingId, String description, Date reportedDate, String status) throws SQLException {
        String sql = "INSERT INTO FAULT_REPORT (Building_ID, Description, Reported_Date, Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, buildingId);
            pstmt.setString(2, description);
            pstmt.setDate(3, reportedDate);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
        }
    }
}
