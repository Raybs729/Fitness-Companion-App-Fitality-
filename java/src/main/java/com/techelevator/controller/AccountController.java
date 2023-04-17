package com.techelevator.controller;

import com.techelevator.dao.AccountDao;
import com.techelevator.dao.JdbcAccountDao;
import com.techelevator.dao.JdbcExerciseDao;
import com.techelevator.model.Account;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**********************************************************************
 ***                 AccountController                              ***
 **                     use to control all                           **
 *       the requests from the client side to end points              *
 **********************************************************************/

@PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("")
    public List<Account> getAllAccounts (){
    List<Account> accounts = new ArrayList<>();
    try {
        accounts = dao.findAll();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
    return accounts;
    }

    @PutMapping("/{user_id}")
    public boolean updateUserInfo (@RequestBody Account account){
        try
        {
        dao.updateUserInfo(account);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @GetMapping("/name/{user_id}")
    public String getFirstName (@PathVariable ("user_id") int userId) {
        return dao.getFirstNameByUserId(userId);
    }

    private BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/final_capstone");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        return dataSource;
    }
}
