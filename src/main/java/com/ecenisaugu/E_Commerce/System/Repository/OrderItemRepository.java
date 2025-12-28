package com.ecenisaugu.E_Commerce.System.Repository;

import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

}
