package com.srivatsan177.e_commerce.store.controllers;

import com.srivatsan177.e_commerce.store.models.Store;
import com.srivatsan177.e_commerce.store.repositories.StoreRepository;
import com.srivatsan177.e_commerce.users.models.Role;
import com.srivatsan177.e_commerce.users.models.User;
import com.srivatsan177.e_commerce.users.repositories.RoleRepository;
import com.srivatsan177.e_commerce.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;
    @PostMapping("/add-store")
    public ResponseEntity<String> addStore(@RequestBody Store store) {
        storeRepository.save(store);
        User user = store.getUser();
        Collection<Role> roles = user.getRoles();
        Role role = roleRepository.findByName("ROLE_SELLER").get();
        roles.add(role);
        userRepository.save(user);
        return new ResponseEntity<>("Store added", HttpStatus.OK);
    }
}
