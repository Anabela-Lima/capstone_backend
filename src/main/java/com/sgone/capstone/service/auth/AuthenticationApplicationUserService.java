package com.sgone.capstone.service.auth;

import com.sgone.capstone.model.auth.ApplicationUserImplUserDetails;
import com.sgone.capstone.model.entity.ApplicationUser;
import com.sgone.capstone.repository.auth.AuthenticationApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.sgone.capstone.security.access_definitions.UserRole.*;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationApplicationUserService implements UserDetailsService {

    private AuthenticationApplicationUserRepository authenticationApplicationUserRepository;

    @Autowired
    public AuthenticationApplicationUserService(
            AuthenticationApplicationUserRepository authenticationApplicationUserRepository
    ) {
        this.authenticationApplicationUserRepository = authenticationApplicationUserRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApplicationUser> userOptional =
                authenticationApplicationUserRepository.findUserByUsername(username);

        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(
                    String.format("User %s does not exist", username)
            );
        }

        ApplicationUser user = userOptional.get();

        Set<SimpleGrantedAuthority> authorities =
                user.getOwner() ? APP_OWNER.getGrantedAuthorities() :
                user.getAdmin() ? APP_ADMIN.getGrantedAuthorities() :
                                  APP_USER.getGrantedAuthorities();

        return new ApplicationUserImplUserDetails(
            user.getUsername(),
            user.getPassword(),
            authorities,
                true,
                true,
                true,
                true
        );
    }
}
