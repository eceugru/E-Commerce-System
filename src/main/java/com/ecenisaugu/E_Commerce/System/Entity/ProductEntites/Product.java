package com.ecenisaugu.E_Commerce.System.Entity.ProductEntites;

import com.ecenisaugu.E_Commerce.System.Entity.Category;
import com.ecenisaugu.E_Commerce.System.Entity.Inventroy;
import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.OrderItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "amount")
    private String productAmount;

    @Column(name = "price")
    private String productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category productCategory;

    @Column(name = "image")
    private String productImage;

    @Column(name = "material")
    private String productMaterial;

    @Column(name = "brand")
    private String productBrand;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItem;



}
