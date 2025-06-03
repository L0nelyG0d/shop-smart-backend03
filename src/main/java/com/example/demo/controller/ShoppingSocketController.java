package com.example.demo.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class ShoppingSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public ShoppingSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendUpdate(UUID shoppingListId, Object updatePayload) {
        messagingTemplate.convertAndSend("/topic/lists/" + shoppingListId, updatePayload);
    }
}
