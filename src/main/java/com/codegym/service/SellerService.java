package com.codegym.service;

import com.codegym.model.AppUser;
import com.codegym.model.Seller;
import com.codegym.repository.ISellerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    @Autowired
    ISellerRepo iSellerRepo;


    public Page<Seller> findAll(Pageable pageable) {
        return iSellerRepo.findAll(pageable);
    }

    public Seller save(Seller seller){
        return iSellerRepo.save(seller);
    }

    public List<Seller>getWaitingAcceptSeller(){
        return iSellerRepo.getWaitingAcceptSeller();
    }

    public void deleteSeller(Long id) {
        iSellerRepo.deleteById(id);
    }

    public Optional<Seller> findById(Long id){
        return iSellerRepo.findById(id);
    }

    public Iterable<Seller> findByName(String name){
        return iSellerRepo.findAllByNameContaining(name);
    }
    

    public List<Seller> showActiveSeller(Boolean isActive){
        return iSellerRepo.findSellerByIsAccept(isActive);
    }

    public Page<Seller> showSeller(Pageable pageable){
        return iSellerRepo.showSeller(pageable);
    }

    public Seller findByAppUser(AppUser appUser) {
        return iSellerRepo.findSellerByAppUser(appUser);
    }

    public Seller findSellerById(Long id) {
        return iSellerRepo.findSellerById(id);
    }

    public List<Seller> filterSellerByNameDown(){
        return iSellerRepo.filterCustomerByNameDesc();
    }

    public List<Seller> filterSellerByNameUp(){
        return iSellerRepo.filterCustomerByNameAsc();
    }
}
