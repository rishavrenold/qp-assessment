package com.example.qp.controller;

import com.example.qp.Entity.GroceryItem;
import com.example.qp.service.GroceryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
class UserController {
    private final GroceryService service;

    public UserController(GroceryService service) {
        this.service = service;
    }

    @GetMapping("/groceries")
    public List<GroceryItem> getAvailableGroceries() {
        return service.getAvailableGroceries();
    }

    @PostMapping("/order")
    public String bookGroceries(@RequestBody List<Long> itemIds) {
        return service.bookGroceries(itemIds);
    }
}

