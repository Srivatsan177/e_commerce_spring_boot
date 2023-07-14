package com.srivatsan177.e_commerce.users.repositories;

import com.srivatsan177.e_commerce.users.models.Address;
import com.srivatsan177.e_commerce.users.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    public Optional<List<Address>> findByUser(User user);
}
