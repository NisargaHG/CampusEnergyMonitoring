package com.campus.energy.model;

public class EnergyMeter {
    private int meterId;
    private int buildingId;
    private String meterType;

    public EnergyMeter(int meterId, int buildingId, String meterType) {
        this.meterId = meterId;
        this.buildingId = buildingId;
        this.meterType = meterType;
    }

    public int getMeterId() { return meterId; }
    public void setMeterId(int meterId) { this.meterId = meterId; }

    public int getBuildingId() { return buildingId; }
    public void setBuildingId(int buildingId) { this.buildingId = buildingId; }

    public String getMeterType() { return meterType; }
    public void setMeterType(String meterType) { this.meterType = meterType; }

    @Override
    public String toString() {
        return "Meter " + meterId + " (" + meterType + ")";
    }
}
