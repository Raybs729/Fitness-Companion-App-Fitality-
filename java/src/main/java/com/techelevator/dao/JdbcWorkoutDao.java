package com.techelevator.dao;

import com.techelevator.model.Workout;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcWorkoutDao implements WorkoutDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /****************************************************************************
     *                              JdbcWorkoutDao                              *
     *                  access Workout Table in database                        *
     ****************************************************************************/

    @Override
    public boolean createWorkout(Workout workout) {
        String sql = "INSERT INTO public.workout(\n" +
                "\tuser_id, start_time)\n" +
                "\tVALUES (?, ?);";

        Integer newWorkoutId = jdbcTemplate.queryForObject(sql, Integer.class, workout.getUserId(), workout.getTimeOfEntry());
        if (newWorkoutId == null) {
            return false;
        }
        return true;
    }

    @Override
    public Workout getWorkoutById(int workoutId) {
        return null; //TODO
    }
}
