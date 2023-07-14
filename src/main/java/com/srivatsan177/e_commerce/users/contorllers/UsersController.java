package com.srivatsan177.e_commerce.users.contorllers;

import com.srivatsan177.e_commerce.users.models.Role;
import com.srivatsan177.e_commerce.users.models.User;
import com.srivatsan177.e_commerce.users.models.UserSignupRequest;
import com.srivatsan177.e_commerce.users.repositories.RoleRepository;
import com.srivatsan177.e_commerce.users.repositories.UserRepository;
import com.srivatsan177.e_commerce.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignupRequest userSignup) {
        // check if username already exists
        try {
            if (userRepository.findByEmail(userSignup.getEmail()).isPresent()) {
                throw new Exception();
            }
            User user = new User(userSignup.getEmail(), passwordEncoder.encode(userSignup.getPassword()));
            System.out.println("Exception " + user.getPassword());
            user.setRoles(new ArrayList<>() {{
                add(roleRepository.findByName("ROLE_USER").get());
            }});
            userRepository.save(user);
            String jwt = jwtUtil.getJWTToken(user.getEmail());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Username already exists", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody UserSignupRequest userSignin) {
        // check if username already exists
        try {
            Optional<User> user = userRepository.findByEmail(userSignin.getEmail());
            if (userRepository.findByEmail(userSignin.getEmail()).isEmpty()) {
                throw new Exception();
            }
            if (!passwordEncoder.matches(userSignin.getPassword(), user.get().getPassword())) {
                return new ResponseEntity<>("Incorrect Password", HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(jwtUtil.getJWTToken(user.get().getEmail()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Email not registered", HttpStatus.FORBIDDEN);
        }
    }
}
