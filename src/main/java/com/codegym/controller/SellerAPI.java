package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sellers")
public class SellerAPI {
    @Autowired
    ProductService productService;
    // lấy sản phẩm theo id người bán
    @GetMapping("/{seller_id}")
    public ResponseEntity<List<Product>> getAllProductBySeller(@PathVariable Long seller_id){
        return new ResponseEntity<>((List<Product>) productService.getAllProductBySeller(seller_id), HttpStatus.OK) ;
    }

    //tạo sản phẩm
    @PostMapping("/save-product")
    public ResponseEntity<Product> save(@RequestBody Product product){
        productService.save(product);
       return new ResponseEntity<>(HttpStatus.OK);
    }

    //xóa sản phẩm
    @PostMapping("/delete-product/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //cập nhật sản phẩm
    @PostMapping("/edit-product/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

}
