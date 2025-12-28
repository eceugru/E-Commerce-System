package com.ecenisaugu.E_Commerce.System.Dto.Request.Inventroy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {
    private String productId;
    private int quantity;
}
