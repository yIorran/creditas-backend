package com.creditas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"com.creditas"})
public class CreditasApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditasApplication.class, args);
    }

}
