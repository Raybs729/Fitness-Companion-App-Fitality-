package com.techelevator.model;

public class EquipmentExercise {
    private int exerciseId;
    private int equipmentId;

    public EquipmentExercise() {}

    public EquipmentExercise(int exerciseId, int equipmentId) {
        this.exerciseId = exerciseId;
        this.equipmentId = equipmentId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }
}
