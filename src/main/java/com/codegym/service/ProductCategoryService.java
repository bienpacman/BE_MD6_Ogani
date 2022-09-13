package com.codegym.service;

import com.codegym.model.ProductCategory;
import com.codegym.repository.IProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductCategoryService {
    @Autowired
    IProductCategoryRepo iProductCategoryRepo;

    public List<ProductCategory> getAllCategory(){
        return iProductCategoryRepo.getAllCategory();
    }

    public ProductCategory findCategoryById(Long id ){
        return iProductCategoryRepo.findProductCategoryById(id);
    }

    public List<ProductCategory> getAllCategories(){
        return iProductCategoryRepo.findAll();
    }
}
