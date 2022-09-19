package com.codegym.service;

import com.codegym.model.ProductComment;
import com.codegym.repository.IProductCommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCommentService {
    @Autowired
    IProductCommentRepo iProductCommentRepo;
    public ProductComment save(ProductComment productComment) {
        return iProductCommentRepo.save(productComment);
    }

    public List<ProductComment> findProductCommentListByProductId(Long idProduct) {
        return iProductCommentRepo.findProductCommentListByProductId(idProduct);
    }
}
