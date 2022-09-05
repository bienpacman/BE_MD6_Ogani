package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductAPI {
    @Autowired
    ProductService productService;
    //Lấy ra toàn bộ sản phẩm
    @GetMapping()
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }
    // lấy sản phẩm theo id người bán
    @GetMapping("/{seller_id}")
    public List<Product> getAllBySeller(@PathVariable Long seller_id){
        return productService.getAllBySeller(seller_id);
    }

    //tạo sản phẩm
    @PostMapping()
    public void save(@RequestBody Product product){
        productService.save(product);
    }

    //xóa sản phẩm
    @PostMapping("/delete-product/{id}")
    public void delete(@PathVariable Long id){
        productService.delete(id);
    }
    //cập nhật sản phẩm
    @PostMapping("/edit-product/{id}")
    public void edit(@RequestBody Product product){
        productService.save(product);
    }
}
