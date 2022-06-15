package com.sgone.capstone.model;

import javax.persistence.*;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password_hash", nullable = false)
    private String password;

    // TODO: User Entity properties goes here
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "mobile", unique = true)
    private Long mobile;
    // TODO: User Entity properties goes here

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
    @Column(name = "is_owner", nullable = false)
    private Boolean isOwner;


    public ApplicationUser() {}

    // TODO: Modify constructor after adding additional properties
    public ApplicationUser(Long id,
                           String username,
                           String password,
                           String email,
                           Long mobile,
                           Boolean isAdmin,
                           Boolean isOwner) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
    }
    // TODO: Modify constructor after adding additional properties
    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           Boolean isAdmin,
                           Boolean isOwner) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
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
