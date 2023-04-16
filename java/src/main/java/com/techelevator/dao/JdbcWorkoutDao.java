package com.techelevator.dao;

import com.techelevator.model.Equipment;
import com.techelevator.model.GymClass;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcWorkoutDao implements WorkoutDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcWorkoutDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /****************************************************************************
     *                              JdbcWorkoutDao                              *
     *              access Workout Table and GymClass in database               *
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

    public void createWorkoutTime(WorkoutTime workoutTime){
        String sql = "INSERT INTO public.workout_time(\n" +
                "\tworkout_id, workout_date, workout_duration)\n" +
                "\tVALUES (?, ?, ?);";

        jdbcTemplate.update(sql, workoutTime.getWorkoutId(), workoutTime.getDate(), workoutTime.getDuration());

    }

    @Override
    public List<WorkoutTime> getWorkoutTimesByUserId(int userId) {
        String sql = "SELECT * FROM workout_time wt " +
                "JOIN workout w ON w.workout_id = wt.workout_id " +
                "WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);

        List <WorkoutTime> list = new ArrayList<>();

        while (results.next()) {
            WorkoutTime workoutTime = mapRowToWorkoutTime(results);
            list.add(workoutTime);
        }

        return list;
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
        workoutTime.setDate(rowSet.getDate("workout_date"));
        workoutTime.setDuration(rowSet.getTime("workout_duration"));

        return workoutTime;
    }
    /*******************************************************
     ***                 GYM CLASS                       ***
     **                                                   **
     *******************************************************/


    public boolean createGymClass (GymClass gymClass){
        String sql = "INSERT INTO gym_class (class_name, datestart, timestart, dateend, timeend, signedup) " +
                "VALUES ( ?, ?, ?, ?, ?, ?); ";
        int row = jdbcTemplate.update(sql, gymClass.getClass_name(), gymClass.getDateStart(), gymClass.getTimeStart(),gymClass.getDateEnd(),gymClass.getTimeEnd(), gymClass.getSignedUp());

        return row == 1 ? true : false;
    }

    public List<GymClass> getGymClassesByName (String class_name){
        String sql =    "SELECT class_id, class_name, datestart, timestart, dateend, timeend, signedup " +
                "FROM gym_class " +
                "WHERE (datestart > NOW()::DATE AND datestart < now()+ interval '30 days' OR (datestart = NOW()::DATE AND timestart >= now()::time)) " +
                "AND class_name LIKE ? " +
                "ORDER BY datestart ASC ";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, "%" + class_name + "%");

        List<GymClass> gymClasses = new ArrayList<>();
        while (rowSet.next()) {
            GymClass gymClass = mapRowToGymClass(rowSet);
            gymClasses.add(gymClass);
        }

        return gymClasses;
    }

    public boolean updateGymClass(GymClass gymclass)
    {
        String sql =    "UPDATE gym_class " +
                "SET class_name = ?, datestart = ?, timestart = ?, dateend = ?, timeend = ?, signedup = ? " +
                "WHERE class_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                gymclass.getClass_name(),
                gymclass.getDateStart(),
                gymclass.getTimeStart(),
                gymclass.getDateEnd(),
                gymclass.getTimeEnd(),
                gymclass.getSignedUp(),
                gymclass.getClassId());

        return rowsAffected == 1 ? true : false;
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
