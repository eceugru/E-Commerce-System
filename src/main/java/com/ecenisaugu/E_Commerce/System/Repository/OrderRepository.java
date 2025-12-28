package com.ecenisaugu.E_Commerce.System.Repository;

import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.Order;
import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
