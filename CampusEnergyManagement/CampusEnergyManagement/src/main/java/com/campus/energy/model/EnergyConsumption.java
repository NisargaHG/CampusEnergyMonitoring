package com.campus.energy.model;

import java.sql.Date;

public class EnergyConsumption {
    private int consumptionId;
    private int meterId;
    private Date date;
    private double unitsConsumed;

    public EnergyConsumption(int consumptionId, int meterId, Date date, double unitsConsumed) {
        this.consumptionId = consumptionId;
        this.meterId = meterId;
        this.date = date;
        this.unitsConsumed = unitsConsumed;
    }

    public int getConsumptionId() { return consumptionId; }
    public int getMeterId() { return meterId; }
    public Date getDate() { return date; }
    public double getUnitsConsumed() { return unitsConsumed; }
}
