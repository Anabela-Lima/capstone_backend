package com.sgone.capstone.dto.request;

import com.sgone.capstone.project.model.ApplicationUser;

import javax.persistence.*;
import java.util.Set;

public class NewFriendDto {

    private Long id;

    private ApplicationUser friend_a;
    private ApplicationUser friend_b;


    public NewFriendDto() {
    }

    public NewFriendDto(Long id, ApplicationUser friend_a, ApplicationUser friend_b) {
        this.friend_a = friend_a;
        this.friend_b = friend_b;
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
}

