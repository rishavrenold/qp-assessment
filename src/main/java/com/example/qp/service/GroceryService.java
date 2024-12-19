package com.example.qp.service;


import com.example.qp.Entity.GroceryItem;
import com.example.qp.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryService {

    @Autowired
    private final GroceryRepository repository;

    public GroceryService(GroceryRepository repository) {
        this.repository = repository;
    }

    public GroceryItem addGrocery(GroceryItem item) {
        return repository.save(item);
    }

    public List<GroceryItem> getAllGroceries() {
        return repository.findAll();
    }

    public void deleteGrocery(Long id) {
        repository.deleteById(id);
    }

    public GroceryItem updateGrocery(Long id, GroceryItem updatedItem) {
        return repository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setPrice(updatedItem.getPrice());
            item.setStock(updatedItem.getStock());
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Grocery item not found"));
    }

    public GroceryItem updateStock(Long id, int stock) {
        return repository.findById(id).map(item -> {
            item.setStock(stock);
            return repository.save(item);
        }).orElseThrow(() -> new RuntimeException("Grocery item not found"));
    }

    public List<GroceryItem> getAvailableGroceries() {
        return repository.findAll().stream()
                .filter(item -> item.getStock() > 0)
                .collect(Collectors.toList());
    }

    public String bookGroceries(List<Long> itemIds) {
        List<GroceryItem> items = repository.findAllById(itemIds);
        boolean allAvailable = items.stream().allMatch(item -> item.getStock() > 0);
        if (!allAvailable) {
            throw new RuntimeException("One or more items are out of stock");
        }
        items.forEach(item -> item.setStock(item.getStock() - 1));
        repository.saveAll(items);
        return "Order placed successfully!";
    }
}
