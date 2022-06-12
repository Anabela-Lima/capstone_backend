package com.sgone.capstone.security;

import com.sgone.capstone.model.ApplicationUser;
import com.sgone.capstone.security.access_definitions.UserPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.sgone.capstone.security.access_definitions.UserPermission.*;
import static com.sgone.capstone.security.access_definitions.UserRole.*;
import static org.springframework.security.core.userdetails.User.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disabling CSRF
        http
                .csrf().disable();

        // Whitelisting the below URLs
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*", "/test")
                .permitAll();

        // API Endpoints with ADMIN and OWNER role access
        http
                .authorizeRequests()
                .antMatchers("/management/user/**")
                .hasAnyRole(APP_ADMIN.name(), APP_OWNER.name());

        // API Endpoints with OWNER role access
//        http
//                .authorizeRequests()
//                .antMatchers("/management/admin/**")
//                .hasRole(APP_OWNER.name());

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/management/admin/get_all")
                    .hasAuthority(APP_ADMIN_READ_ALL.getPermission())
                .antMatchers(HttpMethod.POST, "/management/admin/add_new")
                    .hasAuthority(APP_ADMIN_WRITE_ALL.getPermission())
                .antMatchers(HttpMethod.DELETE, "/management/admin/delete")
                    .hasAuthority(APP_ADMIN_WRITE_ALL.getPermission());

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        // OWNER level user
        UserDetails owner_test = User.builder()
                .username("owner")
                .password(passwordEncoder.encode("password"))
                // ROLE_APP_OWNER is how spring understands it
//                .roles(APP_OWNER.name())
                .authorities(APP_OWNER.getGrantedAuthorities())
                .build();

        // ADMIN level user
        UserDetails admin_test = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
//                .roles(APP_ADMIN.name())
                .authorities(APP_OWNER.getGrantedAuthorities())
                .build();


        // APP USER level user
        UserDetails user_test = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
//                .roles(APP_USER.name())
                .authorities(APP_USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                owner_test,
                admin_test,
                user_test
        );
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//
//
//        return null;
//    }
}
