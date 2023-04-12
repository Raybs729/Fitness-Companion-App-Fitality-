package com.techelevator.controller;

import com.techelevator.dao.ExerciseDao;
import com.techelevator.dao.JdbcExerciseDao;
import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/exercises")
public class ExerciseController {
    private ExerciseDao dao;

    public ExerciseController() {
        this.dao = new JdbcExerciseDao(dataSource());
    }

    @GetMapping("/users/{user_id}")
    public List<ExerciseInfo> findExerciseInfoByUserId(@PathVariable("user_id") int userId) {
        return dao.getExerciseInfoByUserId(userId);
    }

//    @GetMapping("/{equipment_usage_date_time}")
//    public List<ExerciseInfo> findExerciseInfoByDate (@PathVariable("equipment_usage_date_time") int equipmentUsageDateTime) {
//        return dao.getExerciseInfoByEquipmentId(equipmentUsageDateTime);
//    }

    @GetMapping("/{equipment_id}")
    public List<ExerciseInfo> findExerciseInfoByEquipmentId (@PathVariable("equipment_id") int equipmentId) {
        return dao.getExerciseInfoByEquipmentId(equipmentId);
    }

    @PostMapping
    public boolean createExercise (@RequestBody Exercise exercise) {
        return dao.createExercise(exercise);
    }


    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        return dataSource;
    }

}
