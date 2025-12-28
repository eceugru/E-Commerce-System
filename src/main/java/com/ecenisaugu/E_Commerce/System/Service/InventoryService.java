package com.ecenisaugu.E_Commerce.System.Service;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Inventroy.InventoryDto;
import com.ecenisaugu.E_Commerce.System.Entity.Inventroy;
import com.ecenisaugu.E_Commerce.System.Entity.ProductEntites.Product;
import com.ecenisaugu.E_Commerce.System.Repository.InventoryRepository;
import com.ecenisaugu.E_Commerce.System.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;

    public InventoryService(InventoryRepository inventoryRepository, ProductRepository productRepository, CartService cartService) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    public void addInventory(InventoryDto inventoryDto) {
        Inventroy inventroy = new Inventroy();

        Optional<Product> product = productRepository.findById(inventoryDto.getProductId());
        inventroy.setQuantity(inventoryDto.getQuantity());
        inventroy.setProductId(product.get().getProductId());

        inventoryRepository.save(inventroy);

    }


    public void updateInventoryForOrder(String userId){
        // Order yapıldığında
        // sepet bilgisi alınır
        // Inventory' den düşüm yapılır
        Map<String, Integer> cartMap = cartService.getCart(userId);

        cartMap.entrySet().forEach(entry -> {
            Optional<Inventroy> updateInventory = inventoryRepository.findByProductId(entry.getKey());

            // Inventory'den adet düşme
            if (updateInventory.get().getQuantity() >= entry.getValue()) {
                updateInventory.get().setQuantity(updateInventory.get().getQuantity() - entry.getValue());
            }
            inventoryRepository.save(updateInventory.get());
        });

    }

    public void updateInventoryForProduct(InventoryDto inventoryDto) {
        Optional<Inventroy>updateInventory = inventoryRepository.findByProductId(inventoryDto.getProductId());
        if (updateInventory.get().getQuantity() >= inventoryDto.getQuantity()) {
            updateInventory.get().setQuantity(updateInventory.get().getQuantity() +  inventoryDto.getQuantity());
        }else {
            updateInventory.get().setQuantity(0);
        }

        inventoryRepository.save(updateInventory.get());

    }





}
