package com.ecenisaugu.E_Commerce.System.Entity;

import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(name = "description")
    private String categoryDescription;

    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
}
