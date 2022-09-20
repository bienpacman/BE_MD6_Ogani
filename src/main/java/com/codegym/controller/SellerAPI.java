package com.codegym.controller;

import com.codegym.model.*;
import com.codegym.service.*;
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

    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;



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
        productService.isDeleteTrue(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    //cập nhật sản phẩm
    @PostMapping("/edit-product/{idProduct}/{idSeller}")
    public ResponseEntity<Product> edit(@PathVariable Long idProduct,@PathVariable Long idSeller, @RequestBody Product product){
        Seller seller = sellerService.findByAppUser(appUserService.findByUserId(idSeller).get());
        product.setSeller(seller);
        product.setId(idProduct);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/edit-seller/{idSeller}")
    public ResponseEntity<Seller> editSeller(@PathVariable Long idSeller, @RequestBody Seller seller){
        Seller sellerEdit = sellerService.findByAppUser(appUserService.findByUserId(idSeller).get());
        long id =  sellerEdit.getId();
        seller.setId(id);
        sellerService.save(seller);
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

    @PostMapping("/getSeller")
    public ResponseEntity<Seller> getSellerByAppUser(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        Seller seller = sellerService.findByAppUser(appUser);
        return new ResponseEntity<>(seller, HttpStatus.OK);
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

    @GetMapping("/sale/{id}")
    public ResponseEntity<Sale> findSaleById(@PathVariable Long id){
        Sale sale = saleService.findById(id);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }



    //**** Quản lý đơn hàng   ****\\

    // Lấy danh sách order theo tên người bán chưa confirm
    @GetMapping("/orders/{idSeller}")
    public ResponseEntity<List<Order>> getOrderList(@PathVariable Long idSeller){
        Seller sell = sellerService.findByAppUser(appUserService.findByUserId(idSeller).get());
        long id = sell.getId();
        List<Order> orders = orderService.findOrderBySellerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // lấy danh sách order theo tên người bán đã confirm
    @GetMapping("/orders-confirmed/{idSeller}")
    public ResponseEntity<List<Order>> getOrderListConfirmed(@PathVariable Long idSeller){
        Seller sell = sellerService.findByAppUser(appUserService.findByUserId(idSeller).get());
        long id = sell.getId();
        List<Order> orders = orderService.findOrderConfirmedBySellerId(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // Lấy danh sách order chi tiết theo Id Order
    @GetMapping("/order-detail/{id}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailListByOrder(@PathVariable Long id){
        Order order = orderService.findOrderById(id);
        List<OrderDetail> orderDetails = orderDetailService.findOrderDetailByOrder(order);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    @GetMapping("/confirm-order/{id}")
    public void confirmOrder(@PathVariable Long id){
       orderService.confirmOrder(id);
    }

    @GetMapping("/price-up")
    public ResponseEntity<List<Order>> findOrderConfirmedByPriceTotalAsc(){
        List<Order> orders = orderService.findOrderConfirmedByPriceTotalAsc(2);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/price-down")
    public ResponseEntity<List<Order>> findOrderConfirmedByPriceTotalDesc(){
        List<Order> orders = orderService.findOrderConfirmedByPriceTotalDesc(2);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/date-up")
    public ResponseEntity<List<Order>> findOrderConfirmedByDateAsc(){
        List<Order> orders = orderService.findOrderConfirmedByDateAsc(2);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/date-down")
    public ResponseEntity<List<Order>> findOrderConfirmedByDateDesc(){
        List<Order> orders = orderService.findOrderConfirmedByDateDesc(2);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
