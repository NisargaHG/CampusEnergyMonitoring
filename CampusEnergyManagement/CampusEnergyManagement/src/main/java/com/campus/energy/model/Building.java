package com.campus.energy.model;

public class Building {
    private int buildingId;
    private String buildingName;
    private String location;

    public Building(int buildingId, String buildingName, String location) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.location = location;
    }

    public int getBuildingId() { return buildingId; }
    public void setBuildingId(int buildingId) { this.buildingId = buildingId; }

    public String getBuildingName() { return buildingName; }
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return buildingId + " - " + buildingName;
    }
}
