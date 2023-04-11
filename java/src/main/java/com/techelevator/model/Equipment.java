package com.techelevator.model;

public class Equipment {
    private int equipmentId;
    private String equipmentName;
    private String getEquipmentTutorial;
    private String barcode;
    private String equipmentUsageDateTime;

    public Equipment () {}

    public Equipment(int equipmentId, String equipmentName, String getEquipmentTutorial, String barcode, String equipmentUsageDateTime) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.getEquipmentTutorial = getEquipmentTutorial;
        this.barcode = barcode;
        this.equipmentUsageDateTime = equipmentUsageDateTime;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getGetEquipmentTutorial() {
        return getEquipmentTutorial;
    }

    public void setGetEquipmentTutorial(String getEquipmentTutorial) {
        this.getEquipmentTutorial = getEquipmentTutorial;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getEquipmentUsageDateTime() { //only put in getter because we shouldn't have to "set" time/date on machine used
        return equipmentUsageDateTime;
    }
}
