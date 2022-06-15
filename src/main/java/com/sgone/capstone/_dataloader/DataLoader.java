package com.sgone.capstone._dataloader;

import com.sgone.capstone.model.entity.ApplicationUser;
import com.sgone.capstone._dataloader.DataLoaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    // Repository dependency goes here
    private DataLoaderRepository dataLoaderRepository;
    private PasswordEncoder passwordEncoder;

    public DataLoader(){}

    @Autowired
    public DataLoader(
            DataLoaderRepository dataLoaderRepository,
            PasswordEncoder passwordEncoder) {
        this.dataLoaderRepository = dataLoaderRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        dataLoaderRepository.save(
            new ApplicationUser(
                    "admin1",
                    passwordEncoder.encode("password"),
                    "admin1@email.com",
                    1234567890l,
                    true,
                    false
            )
        );

        dataLoaderRepository.save(
                new ApplicationUser(
                        "admin2",
                        passwordEncoder.encode("password"),
                        "admin2@email.com",
                        2345678901l,
                        true,
                        false
                )
        );

        dataLoaderRepository.save(
                new ApplicationUser(
                        "owner1",
                        passwordEncoder.encode("password"),
                        "owner1@email.com",
                        3456789012l,
                        false,
                        true
                )
        );

        dataLoaderRepository.save(
                new ApplicationUser(
                        "owner2",
                        passwordEncoder.encode("password"),
                        "owner2@email.com",
                        4567890123l,
                        false,
                        true
                )
        );
    }
}