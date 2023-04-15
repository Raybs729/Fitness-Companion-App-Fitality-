package com.techelevator.model;

public class Equipment {
    private int equipmentId;
    private String equipmentName;
    private String equipmentTutorial;
    private String barcode;

    public Equipment () {}

    public Equipment(int equipmentId, String equipmentName, String equipmentTutorial) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentTutorial = equipmentTutorial;
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

    public String getEquipmentTutorial() {
        return equipmentTutorial;
    }

    public void setEquipmentTutorial(String equipmentTutorial) {
        this.equipmentTutorial = equipmentTutorial;
    }

}
