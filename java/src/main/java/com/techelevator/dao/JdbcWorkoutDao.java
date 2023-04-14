package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.GymClass;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Workout> checkInListByUser(int user_id) { //user can see all the dates and times they went to the gym
        List<Workout> workoutList = new ArrayList<>();
        String sql = "SELECT workout_id, user_id, start_time\n" +
                "\tFROM public.workout " +
                "\tWHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);


        while (result.next()) {
            workoutList.add(mapRowToWorkout(result));
        }
        return workoutList;
    }
    /**4/12/23**/
    @Override
    public WorkoutTime getTimeByWorkoutId(int workout_id) {
        String sql = "SELECT workout_id, workout_start_time, workout_end_time\n" +
                "\tFROM public.workout_time" +
                "\tWHERE workout_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, workout_id);

        WorkoutTime workoutTime = null;
        if (result.next()) {
            workoutTime = mapRowToWorkoutTime(result);
        }
        return workoutTime;
    }
    public List<GymClass> getUpcomingGymClass (){
        List<GymClass> listOfClasses = new ArrayList<>();
        String sql = "SELECT g.class_id, g.class_name, g.datestart, g.timestart, g.dateend, g.timeend, g.signedup \n" +
                "FROM gym_class g\n" +
                "WHERE datestart > NOW()::DATE AND datestart < now()+ interval '14 days' OR (datestart = NOW()::DATE AND timestart >= now()::time) \n" +
                "ORDER BY datestart ASC; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            listOfClasses.add(mapRowToGymClass(results));
        }
        return listOfClasses;
    }

    private Workout mapRowToWorkout(SqlRowSet rowSet) {
        Workout workout = new Workout();
        workout.setWorkoutId(rowSet.getInt("workout_id"));
        workout.setUserId(rowSet.getInt("user_id"));
        workout.setTimeOfEntry(rowSet.getString("start_time"));
        return workout;
    }

    private WorkoutTime mapRowToWorkoutTime(SqlRowSet rowSet) {
        WorkoutTime workoutTime = new WorkoutTime();
        workoutTime.setWorkoutId(rowSet.getInt("workout_id"));
        workoutTime.setStartTime(rowSet.getTime("workout_start_time"));
        workoutTime.setEndTime(rowSet.getTime("workout_end_time"));

        return workoutTime;
    }
    public GymClass mapRowToGymClass (SqlRowSet rowSet){
        GymClass gymClass = new GymClass();
        gymClass.setClassId(rowSet.getInt("class_id"));
        gymClass.setClass_name(rowSet.getString("class_name"));
        gymClass.setDateStart(rowSet.getDate("datestart"));
        gymClass.setTimeStart(rowSet.getTime("timestart"));
        gymClass.setDateEnd(rowSet.getDate("dateend"));
        gymClass.setTimeEnd(rowSet.getTime("timeend"));
        gymClass.setSignedUp(rowSet.getInt("signedup"));
        return gymClass;
    }
}
