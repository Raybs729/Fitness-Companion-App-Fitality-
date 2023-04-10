package com.techelevator.model;

public class MuscleGroupExercise {
    private int muscleGroupId;
    private int exerciseId;

    public MuscleGroupExercise() {}

    public MuscleGroupExercise(int muscleGroupId, int exerciseId) {
        this.muscleGroupId = muscleGroupId;
        this.exerciseId = exerciseId;
    }

    public int getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(int muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

}
