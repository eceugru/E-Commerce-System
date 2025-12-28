package com.ecenisaugu.E_Commerce.System.Service;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Product.AddProductDto;
import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import com.ecenisaugu.E_Commerce.System.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository  productRepository;



    public void addProduct(AddProductDto productDto) {

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setProductPrice(productDto.getProductPrice());
        product.setProductAmount(productDto.getAmount());
        product.setProductBrand(productDto.getProductBrand());
        product.setProductImage(productDto.getImage());
        product.setProductMaterial(productDto.getProductMaterial());
        product.setGender(productDto.getGender());

        productRepository.save(product);
    }


    public List<Product> getProducts() {
        return productRepository.findAll();
    }


    public void deleteProduct(Product  product) {
        productRepository.delete(product);
    }


}
