package com.codegym.repository;

import com.codegym.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISaleRepo extends JpaRepository<Sale, Long> {
    List<Sale> findSaleBySellerId(Long sellerId);
}
