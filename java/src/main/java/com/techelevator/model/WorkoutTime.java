package com.techelevator.model;
/**4/12/23**/
public class WorkoutTime {
    private int workoutId;
    private String startTime;
    private String endTime;


    public WorkoutTime() {
    }

    public WorkoutTime(int workoutId, String startTime, String endTime) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
