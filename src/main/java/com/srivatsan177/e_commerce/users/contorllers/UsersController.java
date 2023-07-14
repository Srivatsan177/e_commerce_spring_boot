package com.srivatsan177.e_commerce.users.contorllers;

import com.srivatsan177.e_commerce.users.models.Address;
import com.srivatsan177.e_commerce.users.models.Role;
import com.srivatsan177.e_commerce.users.models.User;
import com.srivatsan177.e_commerce.users.models.UserSignupRequest;
import com.srivatsan177.e_commerce.users.repositories.AddressRepository;
import com.srivatsan177.e_commerce.users.repositories.RoleRepository;
import com.srivatsan177.e_commerce.users.repositories.UserRepository;
import com.srivatsan177.e_commerce.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    AddressRepository addressRepository;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignupRequest userSignup) {
        // check if username already exists
        try {
            System.out.println(userRepository.findByEmail(userSignup.getEmail()).isPresent());
            if (userRepository.findByEmail(userSignup.getEmail()).isPresent()) {
                throw new Exception("Username already exists");
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
            e.printStackTrace();
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

    @PostMapping("/address")
    public ResponseEntity<Address> addAddress(@RequestBody Address address, Authentication authentication, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        address.setUser(user);
        addressRepository.save(address);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAddresses(Principal principal) throws Exception {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow(Exception::new);
        List<Address> addresses = addressRepository.findByUser(user).orElseThrow(Exception::new);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
