package com.techelevator.model;

public class MachineMetric {

    private String equipmentName;
    private int totalUsage;

    public MachineMetric(String equipmentName, int totalUsage) {
        this.equipmentName = equipmentName;
        this.totalUsage = totalUsage;
    }

    public MachineMetric() {}

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getTotalUsage() {
        return totalUsage;
    }

    public void setTotalUsage(int totalUsage) {
        this.totalUsage = totalUsage;
    }
}
