package com.codegym.repository;

import com.codegym.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

import java.util.List;

public interface IAppUserRepo extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users join user_role on users.id = user_role.user_id where role_id = 2; ")
    List<AppUser> getAppUserAsSeller();



}

