package com.sgone.capstone.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
//        http.authorizeRequests().anyRequest().permitAll();

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
                .antMatchers("/management/users/**")
                .hasAnyRole(APP_ADMIN.name(), APP_OWNER.name());

        // API Endpoints with OWNER role access
        http
                .authorizeRequests()
                .antMatchers("/management/admins/**")
                .hasRole(APP_OWNER.name());

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
        UserDetails harry = User.builder()
                .username("Harry")
                .password(passwordEncoder.encode("password"))
                .roles(APP_OWNER.name()) // ROLE_APP_OWNER is how spring understands it
                .build();

        // ADMIN level user
        UserDetails bob = User.builder()
                .username("Bob")
                .password(passwordEncoder.encode("password"))
                .roles(APP_ADMIN.name())
                .build();

        // APP USER level user
        UserDetails john = User.builder()
                .username("John")
                .password(passwordEncoder.encode("password"))
                .roles(APP_USER.name())
                .build();

        return new InMemoryUserDetailsManager(
                harry,
                bob,
                john
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
