package com.ecenisaugu.E_Commerce.System.Dto.Request.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductPriceDto {
    private String productId;
    private String productPrice;
}
