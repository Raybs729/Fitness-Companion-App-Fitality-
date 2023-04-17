package com.techelevator.dao;

import com.techelevator.model.Account;

import java.util.List;

public interface AccountDao {
    public List<Account> findAll();
    public Account findAccountByUserId (int userId);
    public boolean createUserinfo(int userId);
    public boolean updateUserInfo(Account account);
    public String getFirstNameByUserId (int userId);
}
