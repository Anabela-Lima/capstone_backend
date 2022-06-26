package com.sgone.capstone.dataloader;

import com.sgone.capstone.project.model.ApplicationUser;

import java.util.ArrayList;


public class DataLoaderUsersArray {

    public ArrayList<ApplicationUser> dataLoaderUsers() {
        ArrayList<ApplicationUser> users = new ArrayList<ApplicationUser>();

        users.add(
                new ApplicationUser(
                    "admin1",
                    "password",
                    "admin1@email.com",
                    1234567890l,
                    "firstname",
                    "lastname",
                    true,
                    false
                ));

        users.add(
                new ApplicationUser(
                        "admin2",
                        "password",
                        "admin2@email.com",
                        2345678901l,
                        "firstname",
                        "lastname",
                        true,
                        false
                ));

        users.add(
                new ApplicationUser(
                    "owner1",
                    "password",
                    "owner1@email.com",
                    3456789012l,
                    "firstname",
                    "lastname",
                    false,
                    true
                ));

        users.add(
                new ApplicationUser(
                        "owner2",
                        "password",
                        "owner2@email.com",
                        4567890123l,
                        "firstname",
                        "lastname",
                        false,
                        true
                )
        );

        users.add(
                new ApplicationUser(
                        "user1",
                        "password",
                        "user1@email.com",
                        9082913814l,
                        "firstname",
                        "lastname",
                        false,
                        false
                )
        );

        users.add(
                new ApplicationUser(
                        "user2",
                        "password",
                        "user2@email.com",
                        908291213814l,
                        "firstname",
                        "lastname",
                        false,
                        false
                )
        );


        return users;
    }
}
