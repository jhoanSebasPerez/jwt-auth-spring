package com.example.jwtauthspring.controllers;

import com.example.jwtauthspring.domain.Product;
import com.example.jwtauthspring.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid Product product){
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.created(URI.create("/products/" + savedProduct.getId())).body(savedProduct);
    }

    @GetMapping
    public List<Product> findAll(){
        return productRepository.findAll();
    }
}
