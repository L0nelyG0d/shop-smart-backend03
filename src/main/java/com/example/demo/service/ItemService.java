package com.example.demo.service;

import com.example.demo.controller.ShoppingSocketController;
import com.example.demo.model.ShoppingL;
import com.example.demo.repository.ShoppingRepository;
import com.example.demo.model.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final ShoppingRepository shoppingRepository;
    private final ShoppingSocketController socketController;


    public ItemService(ItemRepository itemRepository, ShoppingRepository shoppingRepository, ShoppingSocketController socketController){
        this.itemRepository = itemRepository;
        this.shoppingRepository = shoppingRepository;
        this.socketController = socketController;
    }
    public Item addItem(UUID id, String name, int cost){
        ShoppingL shoppingL = shoppingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShoppingList not found"));

        Item item = new Item();
        item.setName(name);
        item.setCost(cost);
        item.setBought(false);

        item.setShopping(shoppingL);

        Item added = itemRepository.save(item);

        socketController.sendUpdate(id, shoppingL.getItems());


        return added;
    }

    public void deleteItem(UUID id, Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (!item.getShopping().getId().equals(id)) {
            throw new RuntimeException("Item does not belong to the specified ShoppingList");
        }


        itemRepository.delete(item);
        socketController.sendUpdate(id, getItems(id));

    }

    public List<Item> getItems(UUID id){
        ShoppingL shoppingL = shoppingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ShoppingList not found"));


        return shoppingL.getItems();
    }


    public Item changeStatus(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setBought(!item.isBought());

        Item updated = itemRepository.save(item);

        socketController.sendUpdate(
                item.getShopping().getId(),
                getItems(item.getShopping().getId())
        );

        return updated;
    }
}
