package com.codegym.controller;

import com.codegym.dto.UserToken;
import com.codegym.model.*;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class LoginAPI {
    @Autowired
    JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    AppUserService appUserService;

    @Autowired
    SellerService sellerService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    MailerController mailerController;


    @PostMapping("/login")
    public UserToken login(@RequestBody AppUser appUser) {
        try {
            // Tạo ra 1 đối tượng Authentication.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtService.createToken(authentication);
            AppUser appUser1 = appUserService.findByUserName(appUser.getUsername());


            for (Role element : appUser1.getRoles()) {
                System.out.println(element);
                if ((element.getName().equals("ROLE_CUSTOMER") || element.getName().equals("ROLE_ADMIN")) && appUser1.getRoles().size() == 1) {
                    return new UserToken(appUser1.getId(), appUser1.getUsername(), token, appUser1.getRoles());
                } else {
                    Seller seller = sellerService.findByAppUser(appUser1);
                    if (seller.getIsAccept()) {
                        return new UserToken(appUser1.getId(), appUser1.getUsername(), token, appUser1.getRoles());
                    } else {
                        return null;
                    }
                }
            }

        } catch (Exception e) {
            return null;
        }
     return null;
    }

    @PostMapping("/registerSeller")
    public ResponseEntity<AppUser> register(@RequestBody Seller seller){
        appUserService.save(seller.getAppUser());
        sellerService.save(seller);
        return new ResponseEntity<>(seller.getAppUser(), HttpStatus.OK);
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<AppUser> register(@RequestBody Customer customer){
        appUserService.save(customer.getAppUser());
        customerService.save(customer);
        mailerController.sendEmail(customer.getAppUser());
        // tạo phương thức tìm Customer theo userName
        Customer customer1 = customerService.findCustomerByUserName(customer.getAppUser().getUsername());
        // add vào address để tạo đối tượng
        Address address = new Address();
        address.setNameAddress(customer1.getAddress());
        address.setCustomer(customer1);
        addressService.save(address);
        return new ResponseEntity<>(customer.getAppUser(), HttpStatus.OK);
    }

    @PostMapping("/checkUserName")
    public ResponseEntity<Boolean> register(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        if (appUser != null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
    }

    @PostMapping("/getSeller")
    public ResponseEntity<Seller> getSellerByAppUser(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        Seller seller = sellerService.findByAppUser(appUser);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomerByAppUser(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        Customer customer = customerService.findCustomerByAppUser(appUser);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

