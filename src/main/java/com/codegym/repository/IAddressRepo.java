package com.codegym.repository;

import com.codegym.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IAddressRepo extends JpaRepository<Address, Long> {
}
