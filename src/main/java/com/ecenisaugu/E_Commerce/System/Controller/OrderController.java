package com.ecenisaugu.E_Commerce.System.Controller;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Order.UpdateOrderDto;
import com.ecenisaugu.E_Commerce.System.Entity.OrderEntites.Order;
import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
import com.ecenisaugu.E_Commerce.System.Enum.OrderStatus;
import com.ecenisaugu.E_Commerce.System.Service.concretes.CartService;
import com.ecenisaugu.E_Commerce.System.Service.concretes.InventoryService;
import com.ecenisaugu.E_Commerce.System.Service.concretes.OrderService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final InventoryService inventoryService;

    public OrderController(OrderService orderService, CartService cartService, InventoryService inventoryService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.inventoryService = inventoryService;
    }


    @GetMapping()
    public Order getOrder(@RequestBody String orderId){
        return orderService.getOrder(orderId);
    }

    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal User user){
        try {
            // Kullanıcıya ait sepet bilgisi
            // cartMap = productId + quantity
            Map<String, Integer> cartMap = cartService.getCart(user.getUserId());
            // Sepet bilgileri ile order oluşturma
            orderService.createOrder(cartMap, user.getUserId());

            // Inventroy'den sileme işlemi
            inventoryService.updateInventoryForOrder(user.getUserId());

            return "Order has been created";
        }catch (Exception e){
            return "Order could not be created " + e.getMessage();
        }
    }

    @PostMapping("/update")
    public String updateOrder(@RequestBody UpdateOrderDto updateOrderDto, @AuthenticationPrincipal User user){
        try {
            orderService.updateOrder(updateOrderDto, user.getUserId());
            return "Order has been updated";
        }catch (Exception e){
            return "Order could not be updated " + e.getMessage();
        }
    }


}
