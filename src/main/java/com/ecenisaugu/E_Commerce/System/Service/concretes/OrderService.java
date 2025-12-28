package com.ecenisaugu.E_Commerce.System.Service.concretes;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Order.UpdateOrderDto;
import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.Order;
import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.OrderItem;
import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import com.ecenisaugu.E_Commerce.System.Enum.OrderStatus;
import com.ecenisaugu.E_Commerce.System.Repository.OrderItemRepository;
import com.ecenisaugu.E_Commerce.System.Repository.OrderRepository;
import com.ecenisaugu.E_Commerce.System.Repository.ProductRepository;
import com.ecenisaugu.E_Commerce.System.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderService {
    /**
     * Burada Order update edilirken ürün sayısı vs. değiştirilemez.
     */
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository  orderRepository;

    public OrderService(ProductRepository productRepository, UserRepository userRepository, OrderItemRepository orderItemRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public void createOrder(Map<String, Integer> cartItems, String userId) {
        Order newOrder = new Order();
        List<OrderItem> orderItemList = new ArrayList<>();
        AtomicInteger totalPrice = new AtomicInteger();

        // Product bilgisi
        cartItems.entrySet().forEach(entry -> {
            OrderItem orderItem = new OrderItem();

            //Product bilgileri getirilir.
            Optional<Product> product = productRepository.findById(entry.getKey().toString());

            // ürünFiyatı * adet
            System.out.println(Double.parseDouble(product.get().getProductPrice()) + 1);
            totalPrice.addAndGet((int) (Double.parseDouble(product.get().getProductPrice())* (int) entry.getValue()));

            // product object
            orderItem.setProduct(product.get());
            //Product price
            orderItem.setPrice(Double.parseDouble(product.get().getProductPrice()));
            //product quantity
            orderItem.setQuantity((int) entry.getValue());



            orderItem.setOrder(newOrder);


            orderItemList.add(orderItem);

        });
        // total price -> Order
        newOrder.setStatus(OrderStatus.CREATED.toString());
        newOrder.setOrderItems(orderItemList);
        newOrder.setTotalPrice(totalPrice.get());
        newOrder.setUser(userRepository.findById(userId).get());
        newOrder.setCreatedAt(String.valueOf(Date.from(Instant.now())));

        orderRepository.save(newOrder);
    }


    public Order getOrder(String orderId){
        return orderRepository.findById(orderId).get();
    }



    public void updateOrder(UpdateOrderDto  updateOrderDto , String userId) {
        // Order'ın bulunması
        Optional<Order> updateOrder = orderRepository.findById(updateOrderDto.getOrderId());
        // Order Update Alanının güncellenmesi
        if (updateOrder.get().getStatus().equals(OrderStatus.COMPLETED.toString())) {
            new RuntimeException("Completed order cannot be updated");
        }
        updateOrder.get().setUpdatedAt(String.valueOf(Date.from(Instant.now())));

        if (updateOrderDto.getStatus().equalsIgnoreCase(OrderStatus.COMPLETED.toString())){
            updateOrderDto.setStatus(OrderStatus.COMPLETED.toString());
        } else if (updateOrderDto.getStatus().equalsIgnoreCase(OrderStatus.CANCELLED.toString())) {
            updateOrderDto.setStatus(OrderStatus.CANCELLED.toString());
        } else if (updateOrderDto.getStatus().equalsIgnoreCase(OrderStatus.PREPARING.toString())) {
            updateOrderDto.setStatus(OrderStatus.PREPARING.toString());
        } else if (updateOrderDto.getStatus().equalsIgnoreCase(OrderStatus.SHIPPED.toString())) {
            updateOrderDto.setStatus(OrderStatus.SHIPPED.toString());
        }

        System.out.println(updateOrderDto.getStatus());
        System.out.println(updateOrderDto.getOrderId());
        updateOrder.get().setStatus(updateOrderDto.getStatus());
        orderRepository.save(updateOrder.get());

    }


}
