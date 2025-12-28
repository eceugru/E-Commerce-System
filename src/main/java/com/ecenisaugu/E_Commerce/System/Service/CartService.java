package com.ecenisaugu.E_Commerce.System.Service;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CartService {
    // Cart bilgileri geçici olduğu iççin bunlar db'ye kaydedilmemelidir.
    /**
     * Burada controllerda kullanılmadan önce product amount bilgisi integer type'ına çevirilmesine gerek yoktur.
     */

    private final StringRedisTemplate stringRedisTemplate;
    private final long CART_TTL_SECONDS = 200;

    public void addToCart(String userId, String productId, int quantity) {
        String cartKey =cartKey(userId);
        stringRedisTemplate.opsForHash().increment(
                cartKey,
                productId,
                quantity
        );
        stringRedisTemplate.expire(cartKey, Duration.ofSeconds(CART_TTL_SECONDS));
    }

    private String cartKey(String userId){
        return "cart:" +  userId;
    }

    public void removeFromCart(String userId, String productId){
        String cartKey =cartKey(userId);
        long newQuantity = stringRedisTemplate.opsForHash().increment(
                cartKey,
                productId,
                -1
        );
        if(newQuantity <= 0){
            stringRedisTemplate.opsForHash().delete(cartKey, productId.toString()); // sadece ürünü siliyor
        }

        // Sepet boşsa silinmeli
        long size = stringRedisTemplate.opsForHash().size(cartKey);
        if(size < 0){
            stringRedisTemplate.delete(cartKey);
        }
    }

    
    public Map<String,Integer> getCart(String userId){
        String cartKey =cartKey(userId);

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(cartKey);

        Map<String,Integer> map = new HashMap<>();

        for(Map.Entry<Object, Object> entry : entries.entrySet()){
            String productId = String.valueOf(entry.getKey().toString());
            int quantity = Integer.valueOf(entry.getValue().toString());
            map.put(productId, quantity);
        }
        return map;
    }


    public void deleteCart(String userId){
        String cartKey = cartKey(userId);
        stringRedisTemplate.delete(cartKey);
    }
    
    
}
