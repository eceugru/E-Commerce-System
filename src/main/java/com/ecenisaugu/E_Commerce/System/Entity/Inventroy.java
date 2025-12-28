package com.ecenisaugu.E_Commerce.System.Entity;

import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Inventroy")
public class Inventroy {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "product_id", unique = true, nullable = false)
    private String productId;

    @Column(name = "quantity")
    private int quantity;
}
