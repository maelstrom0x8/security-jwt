package com.antares.jinsei.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.antares.jinsei.model.Product;
import com.antares.jinsei.repository.ProductRepository;

public class ProductLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
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
