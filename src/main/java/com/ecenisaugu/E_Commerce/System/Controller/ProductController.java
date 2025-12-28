package com.ecenisaugu.E_Commerce.System.Controller;


import com.ecenisaugu.E_Commerce.System.Dto.Request.Product.DeleteProductDto;
import com.ecenisaugu.E_Commerce.System.Dto.Request.Product.AddProductDto;
import com.ecenisaugu.E_Commerce.System.Dto.Request.Product.UpdateProductPriceDto;
import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import com.ecenisaugu.E_Commerce.System.Repository.ProductRepository;
import com.ecenisaugu.E_Commerce.System.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/add")
    public String addProduct(@RequestBody AddProductDto productDto) {
        System.out.println("deneme çıktısıdır ..................");
        try{
            productService.addProduct(productDto);
            return "Product added successfully";
        }catch(Exception e){
            return  "Error adding product";
        }
    }

    @GetMapping("/list")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestBody DeleteProductDto productId) {
        try{
            System.out.println(productId);
            Optional<Product> product = productRepository.findById(productId.getProductId());
            System.out.println(product.toString());
            productService.deleteProduct(product.get());
            return "Product deleted successfully";
        }catch(Exception e){
            return  "Error deleting product" + e.getMessage();
        }
    }

    // sadece amonut - price değikliği
    @PostMapping("/update")
    public String updateProduct(@RequestBody AddProductDto productDto) {
        try{
            Optional<Product> oldProduct = productRepository.findById(productDto.getProductId());
            if(oldProduct.isPresent()){
                Product product = oldProduct.get();
                product.setProductMaterial(productDto.getProductMaterial());
                product.setProductName(productDto.getProductName());
                product.setProductPrice(productDto.getProductPrice());
                product.setProductImage(productDto.getImage());
                product.setProductBrand(productDto.getProductBrand());
                product.setProductAmount(productDto.getAmount());
                product.setGender(productDto.getGender());
                productRepository.save(product);
                return "Product updated successfully";
            }
            else {
                return "Product not found";
            }
        } catch(Exception e){
            return  "Error updating product" + e.getMessage();
        }
    }

    @PostMapping("/update/price")
    public String updateProductPrice(@RequestBody UpdateProductPriceDto updateProductPriceDto) {
        try{
            if (updateProductPriceDto.getProductId() != null) {
                Optional<Product> oldProduct = productRepository.findById(updateProductPriceDto.getProductId());
                if(oldProduct.isPresent()){
                    Product product = oldProduct.get();
                    product.setProductPrice(updateProductPriceDto.getProductPrice());
                    productRepository.save(product);
                    return "Product updated successfully";
                }
                return "Product not found";

            }else{
                return "Product not found";
            }

        } catch (Exception e) {
            return  "Error updating product" + e.getMessage();
        }
    }


}   
    