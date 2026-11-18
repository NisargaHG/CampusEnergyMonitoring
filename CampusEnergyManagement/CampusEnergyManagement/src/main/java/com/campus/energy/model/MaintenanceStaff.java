package com.campus.energy.model;

public class MaintenanceStaff {
    private int staffId;
    private String staffName;
    private String phone;
    private String specialization;

    public MaintenanceStaff(int staffId, String staffName, String phone, String specialization) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.phone = phone;
        this.specialization = specialization;
    }

    public int getStaffId() { return staffId; }
    public String getStaffName() { return staffName; }
    public String getPhone() { return phone; }
    public String getSpecialization() { return specialization; }

    @Override
    public String toString() {
        return staffId + " - " + staffName;
    }
}
