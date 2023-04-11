package com.techelevator.dao;

import com.techelevator.model.Account;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JdbcAccountDao implements AccountDao {
    private final JdbcTemplate jdbcTemplate;


    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /****************************************************************************
     *                              JdbcAccountDao                              *
     *                  access Account Table in database                        *
     ****************************************************************************/
    @Override
    public Account findAccountByUserId (int userId)
    {
        String sql = "SELECT * FROM public.account";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);
        Account account = null;

        if(result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    public Account mapRowToAccount(SqlRowSet rowSet){
        Account account = new Account();
        account.setEmail(rowSet.getString("email"));
        account.setFirstName(rowSet.getString("first_name"));
        account.setLastName(rowSet.getString("last_name"));
        account.setPhone(rowSet.getInt("phone"));
        account.setAge(rowSet.getInt("age"));
        account.setHeight(rowSet.getInt("height"));
        account.setWeight(rowSet.getInt("weight"));
        account.setPhoto(rowSet.getString("photo"));
        account.setGoals(rowSet.getString("goals"));
        return account;
    }

}
