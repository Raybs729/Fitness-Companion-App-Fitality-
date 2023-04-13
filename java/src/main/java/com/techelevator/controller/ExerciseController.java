package com.techelevator.controller;

import com.techelevator.dao.EquipmentDao;
import com.techelevator.dao.ExerciseDao;
import com.techelevator.dao.JdbcEquipmentDao;
import com.techelevator.dao.JdbcExerciseDao;
import com.techelevator.model.EquipmentExercise;
import com.techelevator.model.Exercise;
import com.techelevator.model.ExerciseInfo;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 ***                      ExerciseController                        ***
 **                     use to control all                           **
 *       the requests from the client side to end points              *
 **********************************************************************/
@RestController
@CrossOrigin
@RequestMapping("/exercises")
public class ExerciseController {
    private ExerciseDao dao;

    public ExerciseController() {
        this.dao = new JdbcExerciseDao(dataSource());
    }

    @GetMapping("")
    public List<Exercise> getAllExercise(){
        List<Exercise> exerciseList = new ArrayList<>();
        try {
            exerciseList = dao.getAllExercise();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return exerciseList;
    }

    @GetMapping("/users/{user_id}")
    public List<ExerciseInfo> findExerciseInfoByUserId(@PathVariable("user_id") int userId) {
        return dao.getExerciseInfoByUserId(userId);
    }

    @GetMapping("/{equipment_id}")
    public List<ExerciseInfo> findExerciseInfoByEquipmentId (@PathVariable("equipment_id") int equipmentId) {
        return dao.getExerciseInfoByEquipmentId(equipmentId);
    }

    @PostMapping
    public void makeExercise (@RequestBody EquipmentExercise equipmentExercise) {
        dao.createEquipmentExercise(equipmentExercise);
    }

    @PostMapping("/info")
    public void create (@RequestBody ExerciseInfo exerciseInfo) {
        dao.createEquipmentUsageLog(exerciseInfo);
        dao.createExerciseInfo(exerciseInfo);
    }


    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        return dataSource;
    }

}
