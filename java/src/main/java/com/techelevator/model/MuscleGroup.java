package com.techelevator.model;

public class MuscleGroup {

    private int muscleGroupId;
    private String muscleGroupName;

    public MuscleGroup (){}

    public MuscleGroup(int muscleGroupId, String muscleGroupName) {
        this.muscleGroupId = muscleGroupId;
        this.muscleGroupName = muscleGroupName;
    }

    public int getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(int muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }

    public String getMuscleGroupName() {
        return muscleGroupName;
    }

    public void setMuscleGroupName(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }
}
