package com.ecenisaugu.E_Commerce.System.Dto.Request.Cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {
    private String productId;
    private Integer quantity;
    
}
