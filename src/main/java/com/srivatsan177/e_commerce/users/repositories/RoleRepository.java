package com.srivatsan177.e_commerce.users.repositories;

import com.srivatsan177.e_commerce.users.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    public Optional<Role> findByName(String name);
}
