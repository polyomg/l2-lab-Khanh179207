package com.poly.lab8.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab8.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
}
