package com.codegym.service;

import com.codegym.model.ProductImage;
import com.codegym.repository.IProductImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductImageService {
    @Autowired
    IProductImageRepo iProductImageRepo;
    public List<ProductImage> getProductImageList(Long productId) {
        return iProductImageRepo.findProductImageByProductId(productId);
    }
}
