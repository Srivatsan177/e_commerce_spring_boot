package com.srivatsan177.e_commerce.users.models;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

//    public Collection<Privilege> getPrivileges() {
//        return privileges;
//    }

    public Role() {
    }

    public Role(String name, Collection<User> users) {
        this.name = name;
        this.users = users;
    }
//
//    public void setPrivileges(Collection<Privilege> privileges) {
//        this.privileges = privileges;
//    }

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

//    @ManyToMany
//    @JoinTable(
//            name = "roles_privileges",
//            joinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "privilege_id", referencedColumnName = "id"))
//    private Collection<Privilege> privileges;
}
