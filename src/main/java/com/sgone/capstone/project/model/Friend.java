package com.sgone.capstone.project.model;



import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String friend_a_name;

    @ManyToOne
    @JoinColumn(name = "friend_a_id")
    private ApplicationUser friend_a;

    private String friend_b_name;
    @ManyToOne
    @JoinColumn(name = "friend_b_id")
    private ApplicationUser friend_b;



    public Friend() {
    }

    public Friend(Long id, String friend_a_name, ApplicationUser friend_a, String friend_b_name, ApplicationUser friend_b) {
        this.id = id;
        this.friend_a = friend_a;
        this.friend_b = friend_b;
        this.friend_a_name = friend_a_name;
        this.friend_b_name = friend_b_name;
    }

    public Friend(ApplicationUser friend_a, ApplicationUser friend_b) {
        this.friend_a = friend_a;
        this.friend_b = friend_b;
    }

    public Friend(String friend_a_name, ApplicationUser friend_a, String friend_b_name, ApplicationUser friend_b) {
        this.friend_a = friend_a;
        this.friend_b = friend_b;
        this.friend_a_name = friend_a_name;
        this.friend_b_name = friend_b_name;
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

    public String getFriend_a_name() {
        return friend_a_name;
    }

    public void setFriend_a_name(String friend_a_name) {
        this.friend_a_name = friend_a_name;
    }

    public String getFriend_b_name() {
        return friend_b_name;
    }

    public void setFriend_b_name(String friend_b_name) {
        this.friend_b_name = friend_b_name;
    }
}

//For addFriend logic:

//Search through list of application users by name
//Attach id to friend_b
//Attach current logged in user value to friend_a
//create new friend object containing friend
