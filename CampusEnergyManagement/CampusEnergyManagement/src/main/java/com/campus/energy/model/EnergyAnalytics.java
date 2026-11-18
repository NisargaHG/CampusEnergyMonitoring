package com.campus.energy.model;

public class EnergyAnalytics {
    private int analyticsId;
    private int buildingId;
    private String monthYear;
    private double totalConsumption;
    private double averageDailyUsage;

    public EnergyAnalytics(int analyticsId, int buildingId, String monthYear, double totalConsumption, double averageDailyUsage) {
        this.analyticsId = analyticsId;
        this.buildingId = buildingId;
        this.monthYear = monthYear;
        this.totalConsumption = totalConsumption;
        this.averageDailyUsage = averageDailyUsage;
    }

    public int getAnalyticsId() { return analyticsId; }
    public int getBuildingId() { return buildingId; }
    public String getMonthYear() { return monthYear; }
    public double getTotalConsumption() { return totalConsumption; }
    public double getAverageDailyUsage() { return averageDailyUsage; }
}
