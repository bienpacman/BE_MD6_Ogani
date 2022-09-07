package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
import com.codegym.service.ProductService;
import com.codegym.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/sellers")
public class SellerAPI {
    @Autowired
    ProductService productService;

    @Autowired
    SellerService sellerService;

    @Autowired
    AppUserService appUserService;
    // lấy sản phẩm theo id người bán
    @GetMapping("/show/{page}")
    public ResponseEntity<Page<Product>> getAllProductBySeller(@RequestBody String userName, @PathVariable(required = true)int page){
        AppUser appUser = appUserService.findByUserName(userName);
        Seller seller = sellerService.findByAppUser(appUser);
        Page<Product> products = productService.getAllProductBySeller(seller.getId(), PageRequest.of(page, 5, Sort.by("name")));
        return new ResponseEntity<>(products, HttpStatus.OK) ;
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
