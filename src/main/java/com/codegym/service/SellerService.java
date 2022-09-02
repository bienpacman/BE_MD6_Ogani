package com.codegym.service;

import com.codegym.model.Seller;
import com.codegym.repository.ISellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    ISellerRepo iSellerRepo;

    public Seller save(Seller seller){
        return iSellerRepo.save(seller);
    }
}
