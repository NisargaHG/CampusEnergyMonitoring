package com.campus.energy.dao;

import com.campus.energy.db.DatabaseConnection;
import com.campus.energy.model.Building;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingDAO {
    public List<Building> getAllBuildings() throws SQLException {
        List<Building> list = new ArrayList<>();
        String sql = "SELECT * FROM BUILDING";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Building(rs.getInt("Building_ID"), rs.getString("Building_Name"), rs.getString("Location")));
            }
        }
        return list;
    }

    public void addBuilding(String name, String location) throws SQLException {
        String sql = "INSERT INTO BUILDING (Building_Name, Location) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.executeUpdate();
        }
    }
}
