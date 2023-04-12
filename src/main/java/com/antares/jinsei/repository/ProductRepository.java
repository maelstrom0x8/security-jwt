package com.antares.jinsei.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.antares.jinsei.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
