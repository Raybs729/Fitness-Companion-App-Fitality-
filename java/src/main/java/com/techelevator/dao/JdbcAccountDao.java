package com.techelevator.dao;

import com.techelevator.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAccountDao implements AccountDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /****************************************************************************
     *                              JdbcAccountDao                              *
     *                  access Account Table in database                        *
     ****************************************************************************/
    @Override   /***NEW***/
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while  (results.next()) {
            accounts.add(mapRowToAccount(results));
        }
        return accounts;
    }
    @Override
    public Account findAccountByUserId (int userId)
    {
        String sql = "SELECT * FROM account " +
                "WHERE user_id = ? ;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        Account account = null;

        if(result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }
    /***********************************************
     *                  4/12/2023 (NEW)            *
     *               Need to check                 *
     * *********************************************/

    @Override   /***NEW***/
    public boolean createUserinfo(int userId) {
        String sql = "INSERT INTO account(\n" +
                "\tfirst_name, last_name, email, phone, age, height, weight, goals, photo)\n" +
                "\tVALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        jdbcTemplate.update(sql, userId);
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        return true;
    }

    @Override   /***NEW***/
    public boolean updateUserInfo(Account account) {
        String sql = "UPDATE public.account" +
        "\t SET user_id=?, first_name=?, last_name=?, email=?, phone=?, \n" +
        "\t age=?, height=?, weight= ?, goals= ?, photo= ? \n" +
        "\t WHERE user_id= ?; ";
        jdbcTemplate.update(sql, account.getFirstName(),account.getLastName(),account.getEmail(),account.getPhone(), account.getAge(), account.getHeight(),account.getGoals(), account.getPhoto());
         return true;
    }


/****************************************************/

    public Account mapRowToAccount(SqlRowSet rowSet){
        Account account = new Account();
        account.setUserId(rowSet.getInt("user_id"));
        account.setEmail(rowSet.getString("email"));
        account.setFirstName(rowSet.getString("first_name"));
        account.setLastName(rowSet.getString("last_name"));
        account.setPhone(rowSet.getString("phone"));
        account.setAge(rowSet.getInt("age"));
        account.setHeight(rowSet.getInt("height"));
        account.setWeight(rowSet.getInt("weight"));
        account.setPhoto(rowSet.getString("photo"));
        account.setGoals(rowSet.getString("goals"));
        return account;
    }

}
