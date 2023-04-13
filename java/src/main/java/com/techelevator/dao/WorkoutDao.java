package com.techelevator.dao;

import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;

import java.util.List;

public interface WorkoutDao {

    public boolean createWorkout(Workout workout);
    public Workout getWorkoutById(int workoutId);
    public List<Workout> checkInListByUser(int user_id);
    /**4/12/23**/
    public WorkoutTime getTimeByWorkoutId (int workout_id );
}
