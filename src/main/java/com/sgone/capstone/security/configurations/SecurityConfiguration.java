package com.sgone.capstone.security.configurations;

import com.sgone.capstone.security.jwt.JwtRequestFilter;
import com.sgone.capstone.security.authentication.AuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration{

    private PasswordEncoder passwordEncoder;
    private AuthenticationUserDetailsService authenticationUserDetailsService;
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfiguration(PasswordEncoder passwordEncoder,
                                 AuthenticationUserDetailsService authenticationUserDetailsService,
                                 JwtRequestFilter jwtRequestFilter) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationUserDetailsService = authenticationUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable();

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authenticate")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/sign_up")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/test")
                .permitAll();

        http
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .anyRequest()
                .authenticated();


        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(authenticationUserDetailsService);
        return provider;
    }
}
