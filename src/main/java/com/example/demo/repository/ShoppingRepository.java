package com.example.demo.repository;

import com.example.demo.model.ShoppingL;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ShoppingRepository extends JpaRepository<ShoppingL, UUID> {

    Optional<ShoppingL> findById(UUID id);
}
