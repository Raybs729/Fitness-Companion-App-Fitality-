package com.techelevator.controller;

import com.techelevator.dao.JdbcAccountDao;
import com.techelevator.dao.JdbcWorkoutDao;
import com.techelevator.dao.WorkoutDao;
import com.techelevator.model.GymClass;
import com.techelevator.model.Workout;
import com.techelevator.model.WorkoutTime;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**********************************************************************
 ***                       WorkoutController                        ***
 **                     use to control all                           **
 *       the requests from the client side to end points              *
 **********************************************************************/
@PreAuthorize("isAuthenticated()")
@RestController
@CrossOrigin
@RequestMapping("/workouts")
public class WorkoutController {
    private WorkoutDao dao;

    public WorkoutController () {
        this.dao = new JdbcWorkoutDao(dataSource());
    }
    /**4/12/23**/

    @GetMapping("/getUserWorkout/{user_id}")
    public List<Workout> getUserWorkout(@PathVariable("user_id") int userId){
        return dao.checkInListByUser(userId);
    }

    @PostMapping("/time")
    public void createWorkoutTime(@RequestBody WorkoutTime workoutTime){
        dao.createWorkoutTime(workoutTime);
    }

    @GetMapping("time/{user_id}")
    public List <WorkoutTime> getWorkoutTimesByUserId (@PathVariable("user_id") int userId) {
        return dao.getWorkoutTimesByUserId(userId);
    }

    /***********************************
     ***          GYM CLASS         ***
     *********************************/

    @GetMapping("/gymclass")
    public List<GymClass> getUpcomingGymClass (){
        List<GymClass> classList = new ArrayList<>();
        classList = dao.getUpcomingGymClass();
        return classList;
    }
    @GetMapping("/getclassesbyname")
    public List<GymClass> getGymClassesByName (@RequestParam("class_name") String class_name)
    {
        return dao.getGymClassesByName(class_name);
    }
    @PutMapping("/gymclass/update")
    public boolean updateGymClass (@Valid @RequestBody GymClass gymclass){
        try
        {
            dao.updateGymClass(gymclass);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createclass")
    public boolean createClass(@Valid @RequestBody GymClass gymClass) {
        return dao.createGymClass(gymClass);
    }


    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        return dataSource;
    }
}
