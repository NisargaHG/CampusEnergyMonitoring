package com.campus.energy.model;

import java.sql.Date;

public class RepairTask {
    private int taskId;
    private int faultId;
    private int staffId;
    private Date assignmentDate;
    private Date resolutionDate;
    private String repairStatus;

    public RepairTask(int taskId, int faultId, int staffId, Date assignmentDate, Date resolutionDate, String repairStatus) {
        this.taskId = taskId;
        this.faultId = faultId;
        this.staffId = staffId;
        this.assignmentDate = assignmentDate;
        this.resolutionDate = resolutionDate;
        this.repairStatus = repairStatus;
    }

    public int getTaskId() { return taskId; }
    public int getFaultId() { return faultId; }
    public int getStaffId() { return staffId; }
    public Date getAssignmentDate() { return assignmentDate; }
    public Date getResolutionDate() { return resolutionDate; }
    public String getRepairStatus() { return repairStatus; }
}
