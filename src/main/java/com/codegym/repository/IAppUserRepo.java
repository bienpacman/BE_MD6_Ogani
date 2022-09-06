package com.codegym.repository;

import com.codegym.model.AppUser;
import com.codegym.model.Seller;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IAppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    @Query(nativeQuery = true, value = "select * from users join user_role on users.id = user_role.user_id where role_id = 2; ")
    List<AppUser> getAppUserAsSeller();



}

