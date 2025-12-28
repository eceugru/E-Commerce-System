package com.ecenisaugu.E_Commerce.System.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Cart.AddToCartRequest;
import com.ecenisaugu.E_Commerce.System.Entity.UserEntites.User;
import com.ecenisaugu.E_Commerce.System.Service.CartService;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/cart")
public class CartContoller {

    private final CartService cartService;

    public CartContoller (CartService cartService){
        this.cartService = cartService;
    }


    // sepeti görüntüle
    @GetMapping("/list")
    public Map<String, Integer> getCart(@AuthenticationPrincipal User user) {

        String userId = user.getUserId(); 

         Map<String, Integer> cartMap = cartService.getCart(userId);

        return cartMap;
    }

    // sepete ekle
    @PostMapping("/items")
    public String addToCart(@RequestBody AddToCartRequest request, @AuthenticationPrincipal User user) {
        try {
            String userId = user.getUserId();
            System.out.println("UserId : " + userId);

            cartService.addToCart(userId, request.getProductId(), request.getQuantity());

            return "Product added successfully to cart";
            
        } catch (Exception e) {
            return "Error adding product to cart " + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteToCart(@PathVariable String productId, @AuthenticationPrincipal User user){
        try {
            String userId = user.getUserId();
            cartService.removeFromCart(userId, productId);
            return "Product deleting successfully to cart";
            
        } catch (Exception e) {
            return "Error deleting product to cart " + e.getMessage();
        }
        
    }

    @DeleteMapping("/delete")
    public String deletingCart(@AuthenticationPrincipal User user){
        String userId = user.getUserId();
        try {
            cartService.deleteCart(userId);
            return "Cart deleting seccessfully";
        } catch (Exception e) {
            return "Error deleting cart";
        }
    }

    
    
}
