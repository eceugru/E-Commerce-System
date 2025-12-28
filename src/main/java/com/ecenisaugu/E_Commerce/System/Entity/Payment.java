package com.ecenisaugu.E_Commerce.System.Entity;

import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.Order;
import jakarta.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "status")
    private String status;
}
