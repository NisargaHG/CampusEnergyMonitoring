package com.campus.energy.model;

import java.sql.Date;

public class FaultReport {
    private int faultId;
    private int buildingId;
    private String description;
    private Date reportedDate;
    private String status;

    public FaultReport(int faultId, int buildingId, String description, Date reportedDate, String status) {
        this.faultId = faultId;
        this.buildingId = buildingId;
        this.description = description;
        this.reportedDate = reportedDate;
        this.status = status;
    }

    public int getFaultId() { return faultId; }
    public int getBuildingId() { return buildingId; }
    public String getDescription() { return description; }
    public Date getReportedDate() { return reportedDate; }
    public String getStatus() { return status; }
}
