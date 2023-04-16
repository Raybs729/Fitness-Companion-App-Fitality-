package com.techelevator.model;

public class Workout {
    private int workoutId;
    private int userId;
    private String timeOfEntry;
    private int totalVisited;

    public Workout(){}

    public Workout(int workoutId, int userId, String timeOfEntry,int totalVisited) {
        this.workoutId = workoutId;
        this.userId = userId;
        this.timeOfEntry = timeOfEntry;
        this.totalVisited = totalVisited;
    }
    public int getTotalVisited () { return totalVisited; }
    public void setTotalVisited (int totalVisited){ this.totalVisited = totalVisited ;}
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

    public String getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(String timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }
}
