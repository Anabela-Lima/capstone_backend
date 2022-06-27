package com.sgone.capstone.project.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password_hash", nullable = false)
    private String password;
    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin;
    @Column(name = "is_owner", nullable = false)
    private Boolean isOwner;


    // TODO: User Entity properties goes here
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "mobile", unique = true)
    private Long mobile;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private Set<TripAssignment> tripAssignments;

//    @ManyToMany
//    @JoinTable(
//            name = "trip_assignments",
//            joinColumns = @JoinColumn(name = "application_user_id"),
//            inverseJoinColumns = @JoinColumn(name = "trip_id")
//    )
//    private Set<Trip> trips = new HashSet<>();

    @OneToMany(mappedBy = "applicationUser", cascade = CascadeType.ALL)
    private Set<DayActivityAssignment> dayActivityAssignments;

    @OneToMany(mappedBy = "payee", cascade = CascadeType.ALL)
    private Set<MoneyOwed> payee;
    @OneToMany(mappedBy = "payer", cascade = CascadeType.ALL)
    private Set<MoneyOwed> payer;

    @OneToMany(mappedBy = "friend_a", cascade = CascadeType.ALL)
    private Set<Friend> friends_a;

    @OneToMany(mappedBy = "friend_b", cascade = CascadeType.ALL)
    private Set<Friend> friends_b;
    // TODO: User Entity properties goes here

    public ApplicationUser() {}

    // TODO: Modify constructor after adding additional properties

    public ApplicationUser(Long id,
                           String username,
                           String password,
                           Boolean isAdmin,
                           Boolean isOwner,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Set<TripAssignment> tripAssignments,
                           Set<DayActivityAssignment> dayActivityAssignments,
                           Set<MoneyOwed> payee,
                           Set<MoneyOwed> payer,
                           Set<Friend> friends_a,
                           Set<Friend> friends_b) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tripAssignments = tripAssignments;
        this.dayActivityAssignments = dayActivityAssignments;
        this.payee = payee;
        this.payer = payer;
        this.friends_a = friends_a;
        this.friends_b = friends_b;
    }


    // TODO: Modify constructor after adding additional properties


    public ApplicationUser(String username,
                           String password,
                           String email,
                           Long mobile,
                           String firstname,
                           String lastname,
                           Boolean isAdmin,
                           Boolean isOwner
                           ) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.email = email;
        this.mobile = mobile;
        this.firstname = firstname;
        this.lastname = lastname;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<TripAssignment> getTripAssignments() {
        return tripAssignments;
    }

    public void setTripAssignments(Set<TripAssignment> tripAssignments) {
        this.tripAssignments = tripAssignments;
    }

    public Set<DayActivityAssignment> getDayActivityAssignments() {
        return dayActivityAssignments;
    }

    public void setDayActivityAssignments(Set<DayActivityAssignment> dayActivityAssignments) {
        this.dayActivityAssignments = dayActivityAssignments;
    }

    public Set<MoneyOwed> getPayee() {
        return payee;
    }

    public void setPayee(Set<MoneyOwed> payee) {
        this.payee = payee;
    }

    public Set<MoneyOwed> getPayer() {
        return payer;
    }

    public void setPayer(Set<MoneyOwed> payer) {
        this.payer = payer;
    }

    public Set<Friend> getFriends_a() {
        return friends_a;
    }

    public void setFriends_a(Set<Friend> friends_a) {
        this.friends_a = friends_a;
    }

    public Set<Friend> getFriends_b() {
        return friends_b;
    }

    public void setFriends_b(Set<Friend> friends_b) {
        this.friends_b = friends_b;
    }
}
