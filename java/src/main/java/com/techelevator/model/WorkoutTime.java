package com.techelevator.model;


import java.sql.Date;
import java.sql.Time;

/**4/12/23**/
public class WorkoutTime {
    private int workoutId;
    private Date date;
    private Time duration;


    public WorkoutTime() {
    }

    public WorkoutTime(int workoutId, Date date, Time duration) {
        this.workoutId = workoutId;
        this.date = date;
        this.duration = duration;
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

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
