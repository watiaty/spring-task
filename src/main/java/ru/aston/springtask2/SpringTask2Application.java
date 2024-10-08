package ru.aston.springtask2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class SpringTask2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringTask2Application.class, args);
    }

}
