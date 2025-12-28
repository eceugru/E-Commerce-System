package com.ecenisaugu.E_Commerce.System.Entity.OrderEntites;

import com.ecenisaugu.E_Commerce.System.Entity.Payment;
import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
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
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "status")
    private String status;

    @Column(name = "create_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    private Payment payment;

}
