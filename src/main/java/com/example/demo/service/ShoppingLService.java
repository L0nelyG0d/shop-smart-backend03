package com.example.demo.service;

import com.example.demo.model.ShoppingL;
import com.example.demo.repository.ShoppingRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingLService {

    public ShoppingRepository shoppingRepository;

    public ShoppingLService(ShoppingRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
    }

    public ShoppingL createList(String title) {
        ShoppingL shoppingL = new ShoppingL();
        if (!title.isBlank()) {
            shoppingL.setTitle(title);
        } else {
            shoppingL.setTitle("There is no title");

        }
        shoppingRepository.save(shoppingL);
        return shoppingL;
    }

    public ShoppingL getList(UUID id){


        return shoppingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping list not found"));    }

    public void deleteList(UUID id) {
        shoppingRepository.deleteById(id);
    }

    public ShoppingL getListByTitle(String title) {
        return shoppingRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("List not found"));
    }

    public ShoppingL setStatus(UUID id){
        ShoppingL shoppingL =  shoppingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping list not found"));

        shoppingL.setBought(true);

        return shoppingRepository.save(shoppingL);
    }

}
