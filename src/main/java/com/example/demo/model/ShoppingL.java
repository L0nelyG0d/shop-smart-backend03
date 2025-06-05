package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingL {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private boolean bought;

    @OneToMany(mappedBy = "shopping", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Item> items;

    @ManyToMany
    @JoinTable(
            name = "user_shopping_list",
            joinColumns = @JoinColumn(name = "shopping_list_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}