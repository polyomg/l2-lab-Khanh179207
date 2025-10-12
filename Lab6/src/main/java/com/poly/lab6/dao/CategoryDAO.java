package com.poly.lab6.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
}
