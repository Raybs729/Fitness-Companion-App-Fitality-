package com.techelevator.dao;

import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;
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

    @Override
    public List<ExerciseInfo> getExerciseInfoByUserId(int userId) {
        String sql = "SELECT w.user_id, e.equipment_name, ex.exercise_name, we.set, we.rep, we.weight, we.duration\n" +
                "FROM equipment e\n" +
                "JOIN equipment_exercise ee ON ee.equipment_id = e.equipment_id\n" +
                "JOIN exercise ex ON ex.exercise_id = ee.exercise_id\n" +
                "JOIN workout_exercise we ON we.exercise_id = ex.exercise_id\n" +
                "JOIN workout w ON w.workout_id = we.workout_id\n" +
                "WHERE  w.user_id = ?;";
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
        exerciseInfo.setEquipmentUsageDateTime(rowSet.getString("equipment_usage_date_time"));

        return exerciseInfo;
    }
}
