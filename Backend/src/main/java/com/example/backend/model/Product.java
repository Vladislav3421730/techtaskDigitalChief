package com.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private Long id;
    private String name;
    private String description;
    @Column(name="created_at")
    private LocalDate createdAt;
    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(cascade =CascadeType.ALL,orphanRemoval = true,fetch =  FetchType.LAZY,mappedBy = "product")
    private List<Sku> skuList;
}
