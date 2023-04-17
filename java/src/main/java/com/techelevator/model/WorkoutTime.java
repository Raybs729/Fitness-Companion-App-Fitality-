package com.techelevator.model;

import java.sql.Date;
import java.sql.Timestamp;

public class WorkoutTime {
    private int workoutId;
    private Date date;
    private Timestamp duration;

    public WorkoutTime() {
    }

    public WorkoutTime(int workoutId, Date date, String durationStr) {
        this.workoutId = workoutId;
        this.date = date;
        setDuration(durationStr);
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getDuration() {
        return duration;
    }

    public void setDuration(String durationStr) {
        String fullTimestampStr = this.date.toString() + " " + durationStr;
        this.duration = Timestamp.valueOf(fullTimestampStr);
    }
}
