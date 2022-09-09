package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import java.util.Optional;

public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    Optional<AppUser> findById(Long id);

    @Query(nativeQuery = true, value = "select * from users join user_role on users.id = user_role.user_id where role_id = 2; ")
    List<AppUser> getAppUserAsSeller();



}

