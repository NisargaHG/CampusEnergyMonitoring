package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.RepairTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {
    public List<RepairTask> getAllTasks() throws SQLException {
        List<RepairTask> list = new ArrayList<>();
        String sql = "SELECT * FROM REPAIR_TASK";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new RepairTask(rs.getInt("Task_ID"), rs.getInt("Fault_ID"), rs.getInt("Staff_ID"), rs.getDate("Assignment_Date"), rs.getDate("Resolution_Date"), rs.getString("Repair_Status")));
            }
        }
        return list;
    }

    public void addTask(int faultId, int staffId, Date assignmentDate, String status) throws SQLException {
        String sql = "INSERT INTO REPAIR_TASK (Fault_ID, Staff_ID, Assignment_Date, Repair_Status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, faultId);
            pstmt.setInt(2, staffId);
            pstmt.setDate(3, assignmentDate);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
        }
    }
}
