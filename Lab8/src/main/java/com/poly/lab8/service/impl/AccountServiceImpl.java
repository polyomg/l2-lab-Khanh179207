package com.poly.lab8.service.impl;

import com.poly.lab8.dao.AccountDAO;
import com.poly.lab8.entity.Account;
import com.poly.lab8.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDAO accountDAO;

    @Override
    public Account findById(String username) {
        return accountDAO.findById(username).orElse(null);
    }
}
