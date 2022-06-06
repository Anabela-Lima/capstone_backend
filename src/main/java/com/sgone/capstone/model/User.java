package com.sgone.capstone.model;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String username;
    @Column(name = "password_hash")
    private String password;

    // TODO: User Entity properties goes here

    @Column(name = "is_admin")
    private Boolean isAdmin;
    @Column(name = "is_owner")
    private Boolean isOwner;


    public User() {}

    // TODO: Modify constructor after adding additional properties
    public User(Long id,
                String username,
                String password,
                Boolean isAdmin,
                Boolean isOwner) {
        this.Id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getOwner() {
        return isOwner;
    }

    public void setOwner(Boolean owner) {
        isOwner = owner;
    }
}
