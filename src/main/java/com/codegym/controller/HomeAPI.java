package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.model.ProductCategory;
import com.codegym.model.ProductImage;
import com.codegym.model.Sale;
import com.codegym.service.ProductCategoryService;
import com.codegym.service.ProductImageService;
import com.codegym.service.ProductService;
import com.codegym.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/home")
public class HomeAPI {
    @Autowired
    ProductService productService;

    @Autowired
    SaleService saleService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductCategoryService productCategoryService;


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
    
    @GetMapping("/sale/{sellerId}")
    public ResponseEntity<List<Sale>> getAllSale(@PathVariable Long sellerId) {
        List<Sale> saleList = saleService.getAllSale(sellerId);
        List<Sale> validatedSaleList = new ArrayList<>();
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        System.out.println(timestamp);
        for (Sale sale: saleList) {
            System.out.println(sale.getStartAt());
            System.out.println(sale.getEndAt());
            if( timestamp.before(sale.getStartAt() ) ||(timestamp.after(sale.getStartAt()) && timestamp.before(sale.getEndAt()))){
               validatedSaleList.add(sale);
                System.out.println(sale.getStartAt());
                System.out.println(sale.getEndAt());
            }
        }
        return new ResponseEntity<>(validatedSaleList, HttpStatus.OK);

    }

    @GetMapping("/product-img/{productId}")
    public ResponseEntity<List<ProductImage>> getProductImgList(@PathVariable Long productId){
        List<ProductImage> productImages = productImageService.getProductImageList(productId);
        return new ResponseEntity<>(productImages, HttpStatus.OK);
    }

    @GetMapping("/top-sold")
    public ResponseEntity<List<Product>> getTopSold(){
        List<Product> products = productService.findProductBySold();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-down")
    public ResponseEntity<List<Product>> findProductByPriceDown(){
        List<Product> products = productService.findProductByPriceDown(false);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/price-up")
    public ResponseEntity<List<Product>> findProductByPriceUp(){
        List<Product> products = productService.findProductByPriceUp(false);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ProductCategory>> getAllCategories(){
        List<ProductCategory> categories = productCategoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<Product>> filterByCategory(@PathVariable Long id){
        ProductCategory productCategory = productCategoryService.findCategoryById(id);
        List<Product> products = productService.filterByCategory(productCategory);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


}
