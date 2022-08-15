package com.desafiofinal.praticafinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PraticafinalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraticafinalApplication.class, args);
        // System.out.println( "A SENHA: " + new BCryptPasswordEncoder().encode("xablau")); // TODO tirar essa gambiarra porca e fazer um endpoint de criar usuarios
        System.out.println("sysout na senha do config");
    }

}
