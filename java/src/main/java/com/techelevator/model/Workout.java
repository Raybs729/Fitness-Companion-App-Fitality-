package com.techelevator.model;

public class Workout {
    private int workoutId;
    private int userId;
    private int timeOfEntry;

    public Workout(){}

    public Workout(int workoutId, int userId, int timeOfEntry) {
        this.workoutId = workoutId;
        this.userId = userId;
        this.timeOfEntry = timeOfEntry;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(int timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }
}
