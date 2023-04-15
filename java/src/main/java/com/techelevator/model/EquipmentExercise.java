package com.techelevator.model;

public class EquipmentExercise {
    private String exerciseName;
    private String equipmentName;

    public EquipmentExercise() {}

    public EquipmentExercise(String exerciseName, String equipmentName) {
        this.exerciseName = exerciseName;
        this.equipmentName = equipmentName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }
}
