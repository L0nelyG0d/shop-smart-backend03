package com.example.demo.controller;

import com.example.demo.model.ShoppingL;
import com.example.demo.service.ShoppingLService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/shopping-lists")
public class ShoppingController {

    private final ShoppingLService shoppingService;

    public ShoppingController(ShoppingLService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @PostMapping
    public ResponseEntity<ShoppingL> createShoppingL(@RequestBody ShoppingL shoppingL){
        ShoppingL createdList = shoppingService.createList(shoppingL.getTitle());
        return ResponseEntity.ok(createdList);
    }


    @GetMapping("/add/{id}")
    public ResponseEntity<ShoppingL> getShoppingLById(@PathVariable UUID id){
        ShoppingL shoppingL = shoppingService.getList(id);
        return ResponseEntity.ok(shoppingL);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShoppingL(@PathVariable UUID id){
        try {
            shoppingService.deleteList(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}