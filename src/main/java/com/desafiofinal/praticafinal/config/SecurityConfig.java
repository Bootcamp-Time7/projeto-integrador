package com.desafiofinal.praticafinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
         .httpBasic()
         .and()
         .authorizeHttpRequests()
        //  .antMatchers(HttpMethod.GET, "/product/products").permitAll()
        //  .antMatchers(HttpMethod.GET, "/product/FF").permitAll()
        //  .antMatchers(HttpMethod.GET, "/sectorProducts/1").permitAll()
        //  .antMatchers(HttpMethod.GET, "/sectorProducts/1/V").permitAll()
        //  .antMatchers(HttpMethod.GET, "/sectorProducts/sector/1").permitAll()
        //  .antMatchers(HttpMethod.GET, "/purchases/ListProducts/1").hasRole("BUYER")
        //  .antMatchers(HttpMethod.POST, "/inboundorder/insert").hasRole("SELLER")
        //  .antMatchers(HttpMethod.PUT, "/inboundorder/update").hasRole("SELLER")
        //  .antMatchers(HttpMethod.POST, "/product").hasRole("SELLER")
        //  .antMatchers(HttpMethod.POST, "/purchases/insert").hasRole("BUYER")
        //  .antMatchers(HttpMethod.POST, "/seller").permitAll()
        //  .antMatchers(HttpMethod.PUT, "/purchases/update/1").hasRole("BUYER")
        //  .antMatchers(HttpMethod.POST, "/sector/insert").hasRole("ADEMIR")
        //  .antMatchers(HttpMethod.PUT, "/sectorProducts/dueDate").hasRole("ADEMIR")
         .anyRequest().authenticated()
         .and()
         .csrf().disable();

        return http.build();
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder password = new BCryptPasswordEncoder();        
        System.out.println("senha do config:  " + password);
        return password;
        
    }
}
