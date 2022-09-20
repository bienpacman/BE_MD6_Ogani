package com.codegym.service;

import com.codegym.model.Product;
import com.codegym.model.ProductCategory;
import com.codegym.model.Seller;
import com.codegym.repository.IProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    IProductRepo iProductRepo;

    public Page<Product> getAllProductBySeller(Long id, Pageable pageable){
        Page<Product> products = iProductRepo.findProductBySellerId(id, pageable);
        return products;
    }

    public List<Product> getProductByPriceASC(Long id){
        List<Product> products = iProductRepo.findProductsASC(id);
        return products;
    }

    public List<Product> getProductByPriceDESC(Long id){
        List<Product> products = iProductRepo.findProductsDESC(id);
        return products;
    }

    public Page<Product> getAllProduct(Boolean isDelete, Pageable pageable){
        return iProductRepo.findProductByIsDelete(isDelete, pageable);
    }

    public void save(Product product){
        iProductRepo.save(product);
    }

    public void isDeleteTrue(long id){
        iProductRepo.setIsDeleteTrue(id);
    }

    public Product findProductById(long id){
        return iProductRepo.findProductById(id);
    }

    public List<Product> findProductBySold(){
        return iProductRepo.findProductBySold();
    }

    public List<Product> findProductByPriceDown(Boolean isDelete){
        return iProductRepo.findProductByIsDeleteOrderByPriceDesc(isDelete);
    }

    public List<Product> findProductByPriceUp(Boolean isDelete){
        return iProductRepo.findProductByIsDeleteOrderByPriceAsc(isDelete);
    }

    public List<Product> filterByCategory(Long idProductCategory) {
        return iProductRepo.findProductByProductCategory(idProductCategory);
    }

    public List<Product> findProductBySeller(Seller seller){
        return iProductRepo.findProductBySeller(seller);
    }

    public List<Product> findAllProduct(){
        return iProductRepo.findProductByIsDelete();
    }

    public List<Product> findProductByName(String name){
        return iProductRepo.findProductByNameContaining(name);
    }
}
