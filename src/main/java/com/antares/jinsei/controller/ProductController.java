package com.antares.jinsei.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antares.jinsei.dto.ProductDTO;
import com.antares.jinsei.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> products() {
        return productService.getAllProducts();
    }

}
