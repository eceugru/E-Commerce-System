package com.ecenisaugu.E_Commerce.System.Dto.Request.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderItemDto {
    private String orderItemId;
    private String productId;
    private int quantity;
}
