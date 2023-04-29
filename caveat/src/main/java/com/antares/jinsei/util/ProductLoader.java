package com.antares.jinsei.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.antares.jinsei.model.Product;
import com.antares.jinsei.repository.ProductRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ProductLoader {

    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    void loadProducts() {
        List<Product> products = new ArrayList<>(List.of(
                new Product("quartz-watch"),
                new Product("engineering-journal"),
                new Product("samsung-bud-pro"),
                new Product("hx-drone"),
                new Product("logitech-mouse-g9"),
                new Product("lavender-incense-lantern"),
                new Product("mug-xl"),
                new Product("oraimo-power-bank"),
                new Product("key-chain"),
                new Product("chessboard")));

        productRepository.saveAll(products);
    }

}
