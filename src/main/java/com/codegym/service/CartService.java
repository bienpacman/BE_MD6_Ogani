package com.codegym.service;

import com.codegym.model.Cart;
import com.codegym.model.Seller;
import com.codegym.repository.ICartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    ICartRepo iCartRepo;
    public Cart save(Cart cart){
        return iCartRepo.save(cart);
    }
}
