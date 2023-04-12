package com.techelevator.dao;

import com.techelevator.model.EquipmentUsageLog;
import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;
import com.techelevator.model.WorkoutExercise;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcExerciseDao implements ExerciseDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcExerciseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /****************************************************************************
     *                              JdbcExerciseDao                             *
     *           access Exercise Table and Exercise Info in database            *
     ****************************************************************************/
    @Override   /***NEW***/
    public List<Exercise> getAllExercise() {
        List<Exercise> exercisesList = new ArrayList<>();
        String sql = "SELECT * FROM exercise";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()){
            exercisesList.add(mapRowToExercise(results));
        }
        return exercisesList;
    }

    @Override
    public List<ExerciseInfo> getExerciseInfoByUserId(int userId) {
        String sql = "SELECT w.user_id, e.equipment_name, ex.exercise_name, we.set, we.rep, we.weight, we.duration\n" +
                " FROM equipment e\n" +
                " JOIN equipment_exercise ee ON ee.equipment_id = e.equipment_id\n" +
                " JOIN exercise ex ON ex.exercise_id = ee.exercise_id\n" +
                " JOIN workout_exercise we ON we.exercise_id = ex.exercise_id\n" +
                " JOIN workout w ON w.workout_id = we.workout_id\n" +
                " WHERE  w.user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

        List<ExerciseInfo> list = new ArrayList<>();

        while (result.next()) {
            ExerciseInfo exerciseInfo = mapRowToExerciseInfo(result);

            list.add(exerciseInfo);
        }
        return list;
    }

    @Override
    public List<ExerciseInfo> getExerciseInfoByEquipmentId(int equipmentId) {
        String sql = "SELECT w.user_id, e.equipment_name, ex.exercise_name, we.set, we.rep, we.weight, we.duration\n" +
                "FROM equipment e\n" +
                "JOIN equipment_exercise ee ON ee.equipment_id = e.equipment_id\n" +
                "JOIN exercise ex ON ex.exercise_id = ee.exercise_id\n" +
                "JOIN workout_exercise we ON we.exercise_id = ex.exercise_id\n" +
                "JOIN workout w ON w.workout_id = we.workout_id\n" +
                "WHERE  e.equipment_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentId);

        List<ExerciseInfo> list = new ArrayList<>();

        while (result.next()) {
            ExerciseInfo exerciseInfo = mapRowToExerciseInfo(result);

            list.add(exerciseInfo);
        }
        return list;
    }

    //WHEN USER'S CHOSEN EXERCISE IS NOT AVAILABLE, RUN THIS
    @Override
    public boolean createExercise(Exercise exercise) {
        String sql = "INSERT INTO public.exercise(\n" +
                "\texercise_name)\n" +
                "\tVALUES (?);";

        Integer newExerciseId = jdbcTemplate.queryForObject(sql, Integer.class, exercise.getExerciseName());
        if (newExerciseId == null) {
            return false;
        }
        return true;
    }

    //*** METHOD TO MANUALLY SELECT EXERCISES, REPS, SETS
    //also creates instance of log class, should auto-populate the log table in database
    @Override
    public ExerciseInfo createExerciseInfo(ExerciseInfo exerciseInfo) {
        String sql = "INSERT INTO public.workout_exercise(workout_id, exercise_id, weight, set, rep, duration)" +
                "VALUES (?, (SELECT exercise_id FROM exercise where exercise_name = ?), ?, ?, ?, ?);";

        jdbcTemplate.queryForObject(sql, WorkoutExercise.class, exerciseInfo.getWorkoutId(), exerciseInfo.getExerciseName(), exerciseInfo.getWeightLifted(), exerciseInfo.getSet(), exerciseInfo.getRep(), exerciseInfo.getSingleWorkoutDuration());

        return exerciseInfo;
    }

    @Override
    public EquipmentUsageLog createEquipmentUsageLog(ExerciseInfo exerciseInfo) {
        String sequel = "INSERT INTO equipmentusagelog (user_id, equipment_id, equipment_usage_date_time)" +
                "VALUES (?, (SELECT equipment_id FROM equipment where equipment_name = ?), ?::timestamp);";

        EquipmentUsageLog equipmentUsageLog = jdbcTemplate.queryForObject(sequel, EquipmentUsageLog.class, exerciseInfo.getUserId(), exerciseInfo.getEquipmentName(), exerciseInfo.getEquipmentUsageDateTime());

        return equipmentUsageLog;
    }


//    @Override
//    public List<ExerciseInfo> getExerciseInfoByDate(String equipmentUsageDateTime) {
//        String sql = "SELECT DATE(equipment_usage_date_time), exercise_name, equipment_name, set, rep, weight\n" +
//                "FROM workout w\n" +
//                "JOIN workout_exercise we ON we.workout_id = w.workout_id\n" +
//                "JOIN exercise e ON e.exercise_id = we.exercise_id\n" +
//                "JOIN equipment_exercise ee ON ee.exercise_id = e.exercise_id\n" +
//                "JOIN equipment eq ON eq.equipment_id = ee.equipment_id\n" +
//                "WHERE DATE(equipment_usage_date_time) = ?;";
//
//        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, equipmentUsageDateTime);
//
//        List<ExerciseInfo> list = new ArrayList<>();
//
//        while (result.next()) {
//            ExerciseInfo exerciseInfo = mapRowToExerciseInfo(result);
//
//            list.add(exerciseInfo);
//        }
//        return list;
//    }

    public ExerciseInfo mapRowToExerciseInfo (SqlRowSet rowSet) {
        ExerciseInfo exerciseInfo = new ExerciseInfo();
        exerciseInfo.setUserId(rowSet.getInt("user_id"));
        exerciseInfo.setEquipmentName(rowSet.getString("equipment_name"));
        exerciseInfo.setExerciseName(rowSet.getString("exercise_name"));
        exerciseInfo.setSet(rowSet.getInt("set"));
        exerciseInfo.setRep(rowSet.getInt("rep"));
        exerciseInfo.setWeightLifted(rowSet.getInt("weight"));
        exerciseInfo.setSingleWorkoutDuration(rowSet.getString("duration"));
        return exerciseInfo;
    }
    /***NEW***/
    public Exercise mapRowToExercise (SqlRowSet rowSet) {
        Exercise exercise = new Exercise();
        exercise.setExerciseId(rowSet.getInt("exercise_id"));
        exercise.setExerciseName(rowSet.getString("exercise_name"));
        return exercise;
    }
}
