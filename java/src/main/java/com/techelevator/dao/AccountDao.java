package com.techelevator.dao;

import com.techelevator.model.Account;

public interface AccountDao {
    public Account findAccountByUserId (int userId);

}
