package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepo extends CrudRepository<Role, Long> {
}
