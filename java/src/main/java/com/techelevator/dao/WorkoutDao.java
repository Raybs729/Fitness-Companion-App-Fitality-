package com.techelevator.dao;

import com.techelevator.model.GymClass;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;

import java.util.List;

public interface WorkoutDao {

    public boolean createWorkout(Workout workout);
    public Workout getWorkoutById(int workoutId);
    public List<Workout> checkInListByUser(int user_id);
    public WorkoutTime getTimeByWorkoutId (int workout_id );
    /**4/13/23**/
    public List<GymClass> getUpcomingGymClass ();
}
