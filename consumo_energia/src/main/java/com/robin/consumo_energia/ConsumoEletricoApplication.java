package com.robin.consumo_energia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumoEletricoApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.robin.consumo_energia.ConsumoEletricoApplication.class, args);
    }

}
