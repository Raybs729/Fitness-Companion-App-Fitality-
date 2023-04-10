package com.techelevator.model;

public class WorkoutExercise {

    private int workoutId;
    private int exerciseId;
    private int weightLifted;
    private int set;
    private int rep;
    private int singleWorkoutDuration;

    public WorkoutExercise () {}

    public WorkoutExercise(int workoutId, int exerciseId, int weightLifted, int set, int rep, int singleWorkoutDuration) {
        this.workoutId = workoutId;
        this.exerciseId = exerciseId;
        this.weightLifted = weightLifted;
        this.set = set;
        this.rep = rep;
        this.singleWorkoutDuration = singleWorkoutDuration;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public int getWeightLifted() {
        return weightLifted;
    }

    public void setWeightLifted(int weightLifted) {
        this.weightLifted = weightLifted;
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

    public int getSingleWorkoutDuration() {
        return singleWorkoutDuration;
    }

    public void setSingleWorkoutDuration(int singleWorkoutDuration) {
        this.singleWorkoutDuration = singleWorkoutDuration;
    }
}
