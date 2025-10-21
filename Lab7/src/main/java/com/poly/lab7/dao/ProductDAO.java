package com.poly.lab7.dao;

import com.poly.lab7.entity.Product;
import com.poly.lab7.entity.Report;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

    // Bài 1: JPQL
    @Query("FROM Product o WHERE o.price BETWEEN ?1 AND ?2")
    List<Product> findByPrice(double minPrice, double maxPrice);

    // Bài 2: JPQL
    @Query("FROM Product o WHERE o.name LIKE ?1")
    Page<Product> findByKeywords(String keywords, Pageable pageable);

    // Bài 3: Tổng hợp dữ liệu tồn kho
    @Query("SELECT o.category AS group, SUM(o.price) AS sum, COUNT(o) AS count "
            + "FROM Product o "
            + "GROUP BY o.category "
            + "ORDER BY SUM(o.price) DESC")
    List<Report> getInventoryByCategory();


    // Bài 4: DSL (thay JPQL ở bài 1)
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    // Bài 5: DSL (thay JPQL ở bài 2)
    Page<Product> findAllByNameLike(String keywords, Pageable pageable);
}
