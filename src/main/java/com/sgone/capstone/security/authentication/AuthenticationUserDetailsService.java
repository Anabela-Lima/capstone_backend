package com.sgone.capstone.security.authentication;

import com.sgone.capstone.project.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.sgone.capstone.security.access_definitions.UserRole.*;

import java.util.Optional;
import java.util.Set;

/**
 *  This Service class implements the Spring Security interface <b><i>UserDetailsService</i></b>.
 *  The <b><i>loadUserByUsername</i></b> method must be overridden to provide our own implementation.
 *  <br>
 *  <br>
 *  In this case, we use the <b><i>AuthenticationApplicationUserRepository</i></b> interface to get a user
 *  from our Postgres database, if the user exist we will check its role, <b>OWNER</b>, <b>ADMIN</b> or <b>USER</b>, and apply
 *  the <b>SimpleGrantedAuthority</b> set associated with that role as defined in the <b><i>UserPermission</i></b> Enum class.
 *  <br>
 *  <br>
 *  A new <b><i>UserDetails</i></b> implementation is returned containing the user's username, password and
 *  granted authorities, with the rest of the boolean properties defaulted to True.
 *  <br><br>
 *  <b>This service is only called during the authentication process.</b>
 */
@Service
public class AuthenticationUserDetailsService implements UserDetailsService {

    private AuthenticationUserDetailsRepository authenticationUserDetailsRepository;

    @Autowired
    public AuthenticationUserDetailsService(
            AuthenticationUserDetailsRepository authenticationUserDetailsRepository
    ) {
        this.authenticationUserDetailsRepository = authenticationUserDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ApplicationUser> userOptional =
                authenticationUserDetailsRepository.findUserByUsername(username);

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

        return new AuthenticationUserImplUserDetails(
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
