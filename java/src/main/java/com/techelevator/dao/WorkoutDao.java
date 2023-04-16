package com.techelevator.dao;

import com.techelevator.model.GymClass;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;

import java.util.List;

public interface WorkoutDao {
    /********************  Workout  *****************************/
    public boolean createWorkout(Workout workout);
    public Workout getWorkoutById(int workoutId);
    public List<Workout> checkInListByUser(int user_id);
    public WorkoutTime getTimeByWorkoutId (int workout_id );
    public void createWorkoutTime(WorkoutTime workoutTime);
    public List <WorkoutTime> getWorkoutTimesByUserId (int userId);
    public int getTotalVisitedByUserId (int userId);
    public List<WorkoutTime> getListOfVisitedDateInMonthByUserId(int userId);

    /********************  GYMCLASS  *****************************/
    public List<GymClass> getUpcomingGymClass ();
    public boolean updateGymClass (GymClass gymClass);
    public boolean createGymClass (GymClass gymClass);
    public List<GymClass> getGymClassesByName (String class_name);
    /**************************************************************/
}
