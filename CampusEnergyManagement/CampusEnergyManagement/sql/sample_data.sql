USE campus_energy_management;

INSERT INTO BUILDING (Building_Name, Location) VALUES 
('Computer Science Block', 'North Campus'),
('Central Library', 'Main Quad'),
('Engineering Workshop', 'South Campus'),
('Admin Building', 'Main Entrance'),
('University Hostel A', 'Residential Zone');

INSERT INTO ENERGY_METER (Building_ID, Meter_Type) VALUES 
(1, 'Smart Digital - 3Phase'),
(2, 'Digital Analog'),
(3, 'High Tension Industrial'),
(4, 'Smart Digital - 1Phase'),
(5, 'Sub-Meter Residential');

INSERT INTO ENERGY_CONSUMPTION (Meter_ID, Date, Units_Consumed) VALUES 
(1, '2026-11-01', 450.50),
(1, '2026-11-02', 475.20),
(2, '2026-11-01', 310.00),
(3, '2026-11-01', 1250.75),
(4, '2026-11-01', 180.40),
(5, '2026-11-01', 620.10);

INSERT INTO FAULT_REPORT (Building_ID, Description, Reported_Date, Status) VALUES 
(1, 'Transformer sparking near Server Room', '2026-11-02', 'In Progress'),
(3, 'Short circuit in welding lab panel', '2026-11-03', 'Pending'),
(5, 'Main corridor lights flickering', '2026-11-04', 'Resolved');

INSERT INTO MAINTENANCE_STAFF (Staff_Name, Phone, Specialization) VALUES 
('John Doe', '9876543210', 'High Voltage Systems'),
('Alice Smith', '9123456789', 'Wiring & Lighting'),
('Robert Johnson', '9988776655', 'Transformer & Substation');

INSERT INTO REPAIR_TASK (Fault_ID, Staff_ID, Assignment_Date, Resolution_Date, Repair_Status) VALUES 
(1, 1, '2026-11-02', NULL, 'In Progress'),
(3, 2, '2026-11-04', '2026-11-04', 'Completed');

INSERT INTO ENERGY_ANALYTICS (Building_ID, Month_Year, Total_Consumption, Average_Daily_Usage) VALUES 
(1, '2026-10', 13500.00, 435.48),
(2, '2026-10', 9800.50, 316.15),
(3, '2026-10', 38900.20, 1254.85),
(4, '2026-10', 5600.00, 180.65),
(5, '2026-10', 19200.00, 619.35);
