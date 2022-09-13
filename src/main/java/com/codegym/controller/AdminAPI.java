package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Customer;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
import com.codegym.service.CustomerService;
import com.codegym.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    AppUserService appUserService;

    @Autowired
    SellerService sellerService;

    @Autowired
    MailerController mailerController;

    @Autowired
    CustomerService customerService;

    @GetMapping("/request")
    public ResponseEntity<List<Seller>> showWaitingSeller(){
        List<Seller> sellers = sellerService.getWaitingAcceptSeller();
        if (sellers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sellers, HttpStatus.OK);

    }


    @PostMapping("/accept/{id}")
    public HttpEntity<Seller> acceptSeller(@PathVariable Long id ){
       Optional<Seller> seller = sellerService.findById(id);
       Seller newSeller = seller.get();
        newSeller.setIsAccept(true);
        sellerService.save(newSeller);
        mailerController.sendEmail(newSeller.getAppUser());
        return new HttpEntity<Seller>(newSeller) ;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        sellerService.deleteSeller(id);
    }

    @GetMapping("/showSeller")
    public ResponseEntity<List<Seller>> showSeller(){
        List<Seller> sellers = sellerService.showActiveSeller(true);
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Seller> findSellerById(@PathVariable Long id){
        Seller seller = sellerService.findSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping("/controlSeller/{id}")
    public HttpEntity<Seller> controlSeller(@PathVariable Long id ){
        Optional<Seller> seller = sellerService.findById(id);
        Seller activatedSeller = seller.get();
        if(activatedSeller.getIsActive()){
            activatedSeller.setIsActive(false);
        }else {
            activatedSeller.setIsActive(true);
        }
        sellerService.save(activatedSeller);
        mailerController.sendEmail(activatedSeller.getAppUser());
        return new HttpEntity<Seller>(activatedSeller) ;
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>>showListCustomers(){
        List<Customer> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @PostMapping("/controlCustomer/{id}")
    public ResponseEntity<Customer> controlCustomer(@PathVariable Long id) {
        Customer customer = customerService.findCustomerById(id);
        if(customer.getIsActive()){
            customer.setIsActive(false);
        }else {
            customer.setIsActive(true);
        }
        customerService.saveCustomer(customer);
        mailerController.sendEmail(customer.getAppUser());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}

