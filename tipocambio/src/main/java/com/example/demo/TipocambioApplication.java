package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TipocambioApplication {

    public static void main(String[] args) {
        SpringApplication.run(TipocambioApplication.class, args);
    }
}
