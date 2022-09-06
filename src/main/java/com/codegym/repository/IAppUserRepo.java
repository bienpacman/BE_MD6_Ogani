package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
