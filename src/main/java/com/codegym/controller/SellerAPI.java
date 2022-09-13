package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Product;
import com.codegym.model.Sale;
import com.codegym.model.ProductCategory;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
import com.codegym.service.ProductCategoryService;
import com.codegym.service.ProductService;
import com.codegym.service.SaleService;
import com.codegym.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
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
    ProductCategoryService productCategoryService;

    @Autowired
    AppUserService appUserService;

    @Autowired
    SaleService saleService;


    // lấy sản phẩm theo id người bán
    @PostMapping("/show/{page}")
    public ResponseEntity<Page<Product>> getAllProductBySeller(@RequestBody String userName, @PathVariable(required = true)int page){
        AppUser appUser = appUserService.findByUserName(userName);
        Seller seller = sellerService.findByAppUser(appUser);
        //tham số page là số phần tử lấy ra từ Database
        Page<Product> products = productService.getAllProductBySeller(seller.getId(), PageRequest.of(page, 100, Sort.by("name")));
        return new ResponseEntity<>(products, HttpStatus.OK) ;
    }

    //tạo sản phẩm
    @PostMapping("/save-product/{userId}")
    public ResponseEntity<Product> save(@RequestBody Product product, @PathVariable Long userId){
        Seller seller = sellerService.findByAppUser(appUserService.findByUserId(userId).get());
        product.setSeller(seller);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //xóa sản phẩm
    @GetMapping("/delete-product/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //cập nhật sản phẩm
    @PostMapping("/edit-product/{idSeller}/{idProduct}")
    public ResponseEntity edit(@PathVariable Long idProduct,@PathVariable Long idSeller, @RequestBody Product product){
        product.getSeller().setId(idSeller);
        product.setId(idProduct);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return new ResponseEntity<Product>(productService.findProductById(id), HttpStatus.OK);
    }

    @GetMapping("/get-category")
    public ResponseEntity<List<ProductCategory>> getAllCategory(){
        return new ResponseEntity<>(productCategoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<Seller> getSeller(@PathVariable Long sellerId){
        return new ResponseEntity<>(sellerService.findByAppUser(appUserService.findByUserId(sellerId).get()), HttpStatus.OK);
    }

    // Quản lý khuyến mại
    @PostMapping("/sale/{userName}")
    public ResponseEntity<List<Sale>> showSaleList(@PathVariable String userName){
        System.out.println(userName);
        AppUser appUser = appUserService.findByUserName(userName);
        Seller seller = sellerService.findByAppUser(appUser);
        List<Sale> saleList = saleService.getAllSale(seller.getId());
        Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        for (Sale sale: saleList) {
            if( timestamp.before(sale.getStartAt() ) ||(timestamp.after(sale.getStartAt()) && timestamp.before(sale.getEndAt()))) {
                sale.setStatus(true);
            }else {
                sale.setStatus(false);
            }

        }
        return new ResponseEntity<>(saleList, HttpStatus.OK);
    }

    @PostMapping("/save-sale")
    public ResponseEntity<Sale> save(@RequestBody Sale sale){
        System.out.println(sale);
        saleService.save(sale);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/delete-sale/{id}")
    public void deleteSale(@PathVariable Long id){
        saleService.deleteSale(id);
    }

    @PostMapping("/edit-sale/{id}")
    public ResponseEntity<Sale> editSale(@PathVariable Long id, @RequestBody Sale sale){
        saleService.save(sale);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
