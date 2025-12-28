package com.ecenisaugu.E_Commerce.System.Controller;

import com.ecenisaugu.E_Commerce.System.Dto.Request.Inventroy.InventoryDto;
import com.ecenisaugu.E_Commerce.System.Entity.Inventroy;
import com.ecenisaugu.E_Commerce.System.Repository.InventoryRepository;
import com.ecenisaugu.E_Commerce.System.Service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    private final InventoryRepository inventoryRepository;
    private final InventoryService inventoryService;

    public InventoryController(InventoryRepository inventoryRepository, InventoryService inventoryService) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryService = inventoryService;
    }


    @GetMapping("/list")
    public List<Inventroy> getInventory() {
        return inventoryRepository.findAll();
    }

    @PostMapping("/create")
    public String createInventroyItem(@RequestBody InventoryDto inventoryDto) {
        try{
            inventoryService.addInventory(inventoryDto);
            return "Inventory has been created";
        }catch(Exception e){
            return  "Inventory could not be created " + e.getMessage();
        }
    }

    @PostMapping("/update")
    public String updateInventroyItem(@RequestBody InventoryDto inventoryDto) {
        try{
            inventoryService.updateInventoryForProduct(inventoryDto);
            return "Inventory has been updated";
        }catch(Exception e){
            return  "Inventory could not be updated " + e.getMessage();
        }

    }

}
