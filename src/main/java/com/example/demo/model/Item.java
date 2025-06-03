package com.example.demo.model;

import com.example.demo.model.ShoppingL;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int cost;
    private boolean bought;
    @ManyToOne
    @JoinColumn(name = "shopping_list_id")
    @JsonBackReference
    ShoppingL shopping;
    int productCount;
}
