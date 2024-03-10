package com.example.tortland.config;

import com.example.tortland.service.userDetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final CustomUserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(CustomUserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                //Страницы доступные всем
                .antMatchers("/login", "/users/registration", "/users/remember-password", "/users/change-password/**")
                .permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/styles.css", "/images/**")
                .permitAll()
                .antMatchers(
                        "/users/profile/*",
                        "/users/profile/",
                        "/fillings/add/*",
                        "/customOrders/create/*",
                        "/cakes/search/*",
                        "/cakes/search"
                )
                .not().hasRole("admin")
                .antMatchers(
                        "/users/all",
                        "/users",
                        "/users/allUsers",
                        "/users/detailedInformation/*",
                        "/users/unblock/*",
                        "/users/delete/*",
                        "/users/deleteImage/*",
                        "/cakes/unblock/*",
                        "/cakes/deleteImage/**",
                        "/cakes/deleteImage/*",
                        "/cakes/deleteImage",
                        "/orders",
                        "/orders/update/*",
                        "/orders/delete/*",
                        "/customOrders",
                        "/customOrders/update/*",
                        "/customOrders/delete/*",
                        "/users/acceptApplication/*",
                        "/users/get-application",
                        "/users/rejectApplication/*"
                ).hasRole("admin")
                .antMatchers(
                        "/cakes/update/*",
                        "/cakes/delete/*",
                        "/fillings/update/*",
                        "/fillings/delete/*"
                )
                .hasAnyRole("admin", "CONFECTIONER")
                .antMatchers(
                        "/cakes/add",
                        "/fillings/add/*",
                        "/fillings/add",
                        "/customOrders/delivered/*",
                        "/customOrders/cancelled/*",
                        "/orders/delivered/*",
                        "/orders/cancelled/*"
                        ).hasRole("CONFECTIONER")
                .antMatchers(
                        "/customOrders/create/*",
                        "/orders/create/*",
                        "/users/application"
                ).hasRole("USER")
                .antMatchers(
                        "/users",
                        "/users/profileConfectioner/*",
                        "/users/customOrders/create/*",
                        "/users/profile/*",
                        "/users/updateImage",
                        "/users/update/*",
                        "/users/remember-password-profile",
                        "/users/remember-password-profile/*",
                        "/customOrders/myCustomOrder",
                        "/customOrders/detailed/*",
                        "/customOrders/myCustomOrderClose",
                        "/orders/myOrderClose",
                        "/orders/detailed/*",
                        "/cake/info/*",
                        "/cakes/search/*",
                        "/cakes/search"
                        ).authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login");
        return http.build();
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
