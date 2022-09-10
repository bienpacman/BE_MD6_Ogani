package com.codegym.repository;

import com.codegym.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductImageRepo extends JpaRepository<ProductImage, Long> {
    List<ProductImage> findProductImageByProductId(Long productId);
}
