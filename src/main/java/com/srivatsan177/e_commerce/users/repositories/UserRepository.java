package com.srivatsan177.e_commerce.users.repositories;

import com.srivatsan177.e_commerce.users.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
