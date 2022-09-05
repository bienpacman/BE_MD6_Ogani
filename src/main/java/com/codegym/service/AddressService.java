package com.codegym.service;

import com.codegym.model.Address;
import com.codegym.repository.IAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    IAddressRepo iAddressRepo;

    public Address save(Address address) {
        return iAddressRepo.save(address);
    }
}
