package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.JdbcAccountDao;
import com.techelevator.dao.JdbcExerciseDao;
import com.techelevator.model.Account;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
public class AccountController {
    private AccountDao dao;

    public AccountController () {
        this.dao = new JdbcAccountDao(dataSource());
    }

    @GetMapping ("/{user_id}" )
    public Account getAccountByUserId (@PathVariable ("user_id") int userId) {
        return dao.findAccountByUserId(userId);
    }

    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone1");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        return dataSource;
    }
}
