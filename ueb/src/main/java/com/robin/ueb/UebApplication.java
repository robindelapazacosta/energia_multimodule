package com.robin.ueb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan(basePackages = {"com.robin.*"}) //Para utilizar componentes de otros módulos
//@EntityScan(basePackages = {"com.robin.*"}) //Para utilizar entidades de otros módulos
public class UebApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.robin.ueb.UebApplication.class, args);
    }

}
