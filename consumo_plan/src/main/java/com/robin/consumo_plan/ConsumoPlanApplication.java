package com.robin.consumo_plan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumoPlanApplication {
    public static void main(String[] args) {
        SpringApplication.run(com.robin.consumo_plan.ConsumoPlanApplication.class, args);
    }
}



