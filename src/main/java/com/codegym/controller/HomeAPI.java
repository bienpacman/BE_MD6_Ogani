package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeAPI {
    @Autowired
    ProductService productService;


    @GetMapping("/{page}")
    public ResponseEntity<Page<Product>> findAllProduct(@PathVariable(required = true)int page){
        Page<Product> products = productService.getAllProduct(false, PageRequest.of(page, 5));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/product-detail/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Product product = productService.findProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
