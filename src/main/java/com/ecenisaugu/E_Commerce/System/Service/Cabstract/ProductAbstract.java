package com.ecenisaugu.E_Commerce.System.Service.Cabstract;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Product.AddProductDto;
import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;

import java.util.List;

public interface ProductAbstract {
    void addProduct(AddProductDto productDto);

    List<Product> getProducts();

    void deleteProduct(Product product);
}
