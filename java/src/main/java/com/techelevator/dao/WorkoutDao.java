package com.techelevator.dao;

import com.techelevator.model.Workout;

public interface WorkoutDao {

    public boolean createWorkout(Workout workout);
    public Workout getWorkoutById(int workoutId);

}
