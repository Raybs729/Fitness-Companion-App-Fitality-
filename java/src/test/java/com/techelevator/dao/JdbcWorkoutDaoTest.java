package com.techelevator.dao;

import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;
import com.techelevator.model.WorkoutExercise;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JdbcWorkoutDaoTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private WorkoutDao workoutDao;
    /*****************************************************
     *                                                   *
     *                  Workout Tests                    *
     *                                                   *
     *****************************************************/
    @Test
    public void WhenNewWorkoutIsCreated_ExpectTrue(){
        Workout workout = new Workout();
        workout.setUserId(1);
        workout.setTimeOfEntry(String.valueOf(LocalDateTime.now()));

        boolean result = workoutDao.createWorkout(workout);

        assertEquals("Expected createWorkout to return true", true, result);
    }
    @Test(expected = DataIntegrityViolationException.class)
    public void createWorkoutThrowsDataIntegrityViolationExceptionWhenUserIdIsNull() {
        Workout workout = new Workout();
        workout.setTimeOfEntry(String.valueOf(LocalDateTime.now()));

        workoutDao.createWorkout(workout);
    }

    @Test
    public void checkInListByUserReturnsListOfWorkouts() {
        int userId = 1;
        LocalDateTime startTime = LocalDateTime.now();
        Workout workout1 = new Workout();
        workout1.setUserId(userId);
        workout1.setTimeOfEntry(String.valueOf(startTime));
        workoutDao.createWorkout(workout1);

        LocalDateTime startTime2 = LocalDateTime.now().minusDays(1);
        Workout workout2 = new Workout();
        workout2.setUserId(userId);
        workout2.setTimeOfEntry(String.valueOf(startTime2));
        workoutDao.createWorkout(workout2);

        List<Workout> workoutList = workoutDao.checkInListByUser(userId);

        assertNotNull(workoutList);
        assertEquals(2, workoutList.size());
    }
    @Test
    public void checkInListByUserReturnsEmptyListWhenUserHasNoWorkouts() {
        int userId = 1;

        List<Workout> workoutList = workoutDao.checkInListByUser(userId);

        assertNotNull(workoutList);
        assertEquals(0, workoutList.size());
    }

    @Test
    public void testGetTimeByWorkoutId() {
        // Set up test data
        int workoutId = 123;
        WorkoutTime expectedWorkoutTime = new WorkoutTime();
        expectedWorkoutTime.setWorkoutId(workoutId);
        expectedWorkoutTime.setDuration(Time.valueOf(60:00:00));
        // Insert the expected data into the database
        jdbcTemplate.update(
                "INSERT INTO public.workout_time (workout_id, workout_start_time, workout_end_time) VALUES (?, ?, ?)",
                workoutId,
                Timestamp.valueOf(expectedWorkoutTime.getDuration());

        );

        // Invoke the method being tested
        WorkoutTime actualWorkoutTime = workoutDao.getTimeByWorkoutId(workoutId);

        // Assert that the expected WorkoutTime object was returned
        assertEquals(expectedWorkoutTime, actualWorkoutTime);
    }


}
