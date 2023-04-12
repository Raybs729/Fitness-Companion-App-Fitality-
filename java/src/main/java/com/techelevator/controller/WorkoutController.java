package com.techelevator.controller;

import com.techelevator.dao.JdbcAccountDao;
import com.techelevator.dao.JdbcWorkoutDao;
import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.Workout;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.*;
/**********************************************************************
 ***                       WorkoutController                        ***
 **                     use to control all                           **
 *       the requests from the client side to end points              *
 **********************************************************************/
@RestController
@CrossOrigin
@RequestMapping("/workouts")
public class WorkoutController {
    private WorkoutDao dao;

    public WorkoutController () {
        this.dao = new JdbcWorkoutDao(dataSource());
    }

//    @GetMapping("/{workout_id}")
//    public Workout getWorkoutById (@PathVariable("workout_id") int workout_id) {
//        return dao. //TODO
//    }

    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        return dataSource;
    }
}
