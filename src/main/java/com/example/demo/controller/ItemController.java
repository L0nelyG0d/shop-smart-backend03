package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.ShoppingL;
import com.example.demo.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @DeleteMapping("/delete/{listId}/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID listId, @PathVariable Long id){
        try{
            itemService.deleteItem(listId, id);
            return ResponseEntity.noContent().build();
        }
        catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<Item>> getItems(@PathVariable UUID id) {

        return ResponseEntity.ok(itemService.getItems(id));
    }


    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody Item item){
        ShoppingL shopping = item.getShopping();

        Item createdItem = itemService.addItem(shopping.getId(), item.getName(), item.getCost());

        return ResponseEntity.ok(createdItem);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> updateStatus(@PathVariable Long id){

        return ResponseEntity.ok(itemService.changeStatus(id));
    }
}
