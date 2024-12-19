package com.example.qp.controller;


import com.example.qp.Entity.GroceryItem;
import com.example.qp.service.GroceryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
class AdminController {
    private final GroceryService service;

    public AdminController(GroceryService service) {
        this.service = service;
    }

    @PostMapping("/grocery")
    public GroceryItem addGrocery(@RequestBody GroceryItem item) {
        return service.addGrocery(item);
    }

    @GetMapping("/groceries")
    public List<GroceryItem> getGroceries() {
        return service.getAllGroceries();
    }

    @DeleteMapping("/grocery/{id}")
    public void deleteGrocery(@PathVariable Long id) {
        service.deleteGrocery(id);
    }

    @PutMapping("/grocery/{id}")
    public GroceryItem updateGrocery(@PathVariable Long id, @RequestBody GroceryItem updatedItem) {
        return service.updateGrocery(id, updatedItem);
    }

    @PutMapping("/grocery/{id}/stock")
    public GroceryItem updateStock(@PathVariable Long id, @RequestParam int stock) {
        return service.updateStock(id, stock);
    }
}