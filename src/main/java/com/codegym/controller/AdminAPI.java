package com.codegym.controller;

import com.codegym.model.AppUser;
import com.codegym.model.Product;
import com.codegym.model.Seller;
import com.codegym.service.AppUserService;
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


//    @GetMapping("/{isActive}")
//    public ResponseEntity<?> showListSeller(@PathVariable Boolean isActive,@PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
//        Page<Seller> sellers = sellerService.showActiveSeller(isActive,pageable);
//        if (sellers.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(sellers, HttpStatus.OK);
//    }

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

    @GetMapping("/show/{page}")
    public ResponseEntity<Page<Seller>> showSeller(@PathVariable(required = true)int page){
        Page<Seller> sellers = sellerService.showActiveSeller(true,PageRequest.of(page, 5, Sort.by("name")));
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

}

