package com.techelevator.model;

import java.sql.Timestamp;

public class Workout {
    private int workoutId;
    private int userId;
    private Timestamp timeOfEntry;

    public Workout(){}

    public Workout(int workoutId, int userId, Timestamp timeOfEntry) {
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

    public Timestamp getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(String timeOfEntryStr) {
        Timestamp timestamp = Timestamp.valueOf(timeOfEntryStr);
        this.timeOfEntry = timestamp;
    }
}