package com.ecenisaugu.E_Commerce.System.Dto.Response.Order;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateOrder {
    private List<CreateOrderItemDto> items;
    private int totalPrice;
}
