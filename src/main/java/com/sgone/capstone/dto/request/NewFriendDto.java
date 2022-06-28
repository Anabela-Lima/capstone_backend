package com.sgone.capstone.dto.request;

import com.sgone.capstone.project.model.ApplicationUser;

import javax.persistence.*;

public class NewFriendDto {

    private Long friend_a_id;
    private Long friend_b_id;

    public NewFriendDto() {
    }

    public NewFriendDto(Long friend_a_id, Long friend_b_id) {
        this.friend_a_id = friend_a_id;
        this.friend_b_id = friend_b_id;
    }

    public Long getFriend_a_id() {
        return friend_a_id;
    }

    public void setFriend_a_id(Long friend_a_id) {
        this.friend_a_id = friend_a_id;
    }

    public Long getFriend_b_id() {
        return friend_b_id;
    }

    public void setFriend_b_id(Long friend_b_id) {
        this.friend_b_id = friend_b_id;
    }
}

