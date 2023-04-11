package com.techelevator.model;

public class EquipmentUsageLog {
    private int equipmentId;
    private int userId;
    private int equipmentUsageDateTime;

    public EquipmentUsageLog() {
    }

    public EquipmentUsageLog(int equipmentId, int userId, int equipmentUsageDateTime) {
        this.equipmentId = equipmentId;
        this.userId = userId;
        this.equipmentUsageDateTime = equipmentUsageDateTime;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEquipmentUsageDateTime() {
        return equipmentUsageDateTime;
    }

    public void setEquipmentUsageDateTime(int equipmentUsageDateTime) {
        this.equipmentUsageDateTime = equipmentUsageDateTime;
    }
}
