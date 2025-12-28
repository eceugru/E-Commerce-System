package com.ecenisaugu.E_Commerce.System.Dto.Request.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDto {
    private String orderId;
    private String status;


}
