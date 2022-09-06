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

    public List<Product> getAllProductBySeller(Long id){
        List<Product> products = iProductRepo.getAllProductBySellerId(id);
        return products;
    }
    public List<Product> getAllProduct(){
        List<Product> products = iProductRepo.getAllProduct();
        return products;
    }

    public void save(Product product){
        iProductRepo.save(product);
    }

    public void delete(long id){
        iProductRepo.deleteById(id);
    }

    public Product getProductById(long id){
        return iProductRepo.getProductById(id);
    }
}
