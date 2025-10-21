package com.poly.lab6.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
    @Id
    private String id;
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @ToString.Exclude // tránh vòng lặp khi in ra
    private List<Product> products;
}
