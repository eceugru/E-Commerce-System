package com.ecenisaugu.E_Commerce.System.Dto.Request.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {
    private String productId;
    private String productName;
    private String amount;
    private String productPrice;
    private String image;
    private String productMaterial;
    private String productBrand;
    private String gender;
}
