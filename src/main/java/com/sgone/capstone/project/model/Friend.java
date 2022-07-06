package com.sgone.capstone.project.model;



import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "friend_a_id")
    private ApplicationUser friend_a;


    @ManyToOne
    @JoinColumn(name = "friend_b_id")
    private ApplicationUser friend_b;

    @Column
    private String username_a;
    @Column
    private String username_b;

    public Friend() {
    }

    public Friend(Long id, ApplicationUser friend_a, ApplicationUser friend_b, String username_a, String username_b) {
        this.id = id;
        this.friend_a = friend_a;
        this.friend_b = friend_b;
        this.username_a = username_a;
        this.username_b = username_b;
    }

    // test constructor
    public Friend(ApplicationUser friend_a, ApplicationUser friend_b) {
        this.friend_a = friend_a;
        this.friend_b = friend_b;
    }

    public Friend(ApplicationUser friend_a, ApplicationUser friend_b , String username_a, String username_b){
        this.friend_a = friend_a;
        this.friend_b = friend_b;
        this.username_a = username_a;
        this.username_b = username_b;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationUser getFriend_a() {
        return friend_a;
    }

    public void setFriend_a(ApplicationUser friend_a) {
        this.friend_a = friend_a;
    }

    public ApplicationUser getFriend_b() {
        return friend_b;
    }

    public void setFriend_b(ApplicationUser friend_b) {
        this.friend_b = friend_b;
    }

    public String getUsername_a() {
        return username_a;
    }

    public void setUsername_a(String username_a) {
        this.username_a = username_a;
    }

    public String getUsername_b() {
        return username_b;
    }

    public void setUsername_b(String username_b) {
        this.username_b = username_b;
    }
}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend
