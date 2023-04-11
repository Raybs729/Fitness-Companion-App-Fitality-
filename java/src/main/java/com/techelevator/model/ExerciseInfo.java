package com.techelevator.model;

public class ExerciseInfo {
    private int userId;
    private String equipmentName;
    private String exerciseName;
    private int set;
    private int rep;
    private int weightLifted;
    private String singleWorkoutDuration;
    private String equipmentUsageDateTime;

    public ExerciseInfo() {
    }

    public ExerciseInfo(int userId, String equipmentName, String exerciseName, int set, int rep, int weightLifted, String singleWorkoutDuration, String equipmentUsageDateTime) {
        this.userId = userId;
        this.equipmentName = equipmentName;
        this.exerciseName = exerciseName;
        this.set = set;
        this.rep = rep;
        this.weightLifted = weightLifted;
        this.singleWorkoutDuration = singleWorkoutDuration;
        this.equipmentUsageDateTime = equipmentUsageDateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getWeightLifted() {
        return weightLifted;
    }

    public void setWeightLifted(int weightLifted) {
        this.weightLifted = weightLifted;
    }

    public String getSingleWorkoutDuration() {
        return singleWorkoutDuration;
    }

    public void setSingleWorkoutDuration(String singleWorkoutDuration) {
        this.singleWorkoutDuration = singleWorkoutDuration;
    }

    public String getEquipmentUsageDateTime() {
        return equipmentUsageDateTime;
    }

    public void setEquipmentUsageDateTime(String equipmentUsageDateTime) {
        this.equipmentUsageDateTime = equipmentUsageDateTime;
    }
}
