package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.model.Seller;
import com.codegym.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepo iProductRepo;

    public List<Product> getAllBySeller(Seller seller){
        List<Product> products = iProductRepo.getAllBySeller(seller);
        return products;
    }

    public void save(Product product){
        iProductRepo.save(product);
    }

    public void delete(long id){
        iProductRepo.deleteById(id);
    }

    public Product findById(long id){
        return iProductRepo.findById(id).get();
    }
}
