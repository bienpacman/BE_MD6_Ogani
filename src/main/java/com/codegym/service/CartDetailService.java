package com.codegym.service;

import com.codegym.model.CartDetail;
import com.codegym.repository.ICartDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartDetailService {
    @Autowired
    ICartDetailRepo iCartDetailRepo;

    public CartDetail save(CartDetail cartDetail) {
        return iCartDetailRepo.save(cartDetail);
    }
}
