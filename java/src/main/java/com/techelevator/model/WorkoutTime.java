package com.techelevator.model;


import java.sql.Time;

/**4/12/23**/
public class WorkoutTime {
    private int workoutId;
    private Time startTime;
    private Time endTime;


    public WorkoutTime() {
    }

    public WorkoutTime(int workoutId, Time startTime, Time endTime) {
        this.workoutId = workoutId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public long timeDifferentInMinutes() {
        long time = (endTime.getTime() - startTime.getTime()) / 60000;
        return time;
    }

}
