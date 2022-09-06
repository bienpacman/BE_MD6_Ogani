package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
import com.codegym.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin")
public class AdminAPI {
    @Autowired
    AppUserService appUserService;

    @Autowired
    SellerService sellerService;


    @GetMapping
    public ResponseEntity<?> showListMerchant(@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<Seller> sellers = sellerService.findAll(pageable);
        if (sellers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @GetMapping("/request")
    public ResponseEntity<List<Seller>> showWaitingSeller(){
        List<Seller> sellers = sellerService.getWaitingAcceptSeller();
        if (sellers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(sellers, HttpStatus.OK);

    }


    @GetMapping("/search")
    public Iterable<Seller> findByName(@RequestParam(defaultValue = "")String name){
        return sellerService.findByName(name);
    }

    @PostMapping("/accept")
    public HttpEntity<Seller> acceptSeller(@RequestBody Seller seller){
       seller.setIsAccept(true);
        return new HttpEntity<Seller>(sellerService.save(seller)) ;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        sellerService.deleteSeller(id);
    }

}

