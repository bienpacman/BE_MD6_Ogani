package com.codegym.service;

import com.codegym.model.Sale;
import com.codegym.repository.ISaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    ISaleRepo iSaleRepo;

    public List<Sale> getAllSale(Long sellerId) {
        return iSaleRepo.findSaleBySellerId(sellerId);
    }


}
