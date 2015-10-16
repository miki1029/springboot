package com.example;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by miki on 15. 10. 10..
 */
@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {
    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... strings) throws Exception {
        // 데이터 추가
        customerService.save(new Customer(1, "Minwoo", "Kim"));
        customerService.save(new Customer(2, "Kisang", "Kang"));
        customerService.save(new Customer(3, "Sangjun", "Park"));
        customerService.save(new Customer(4, "Woong", "Oh"));

        // 데이터 표시
        customerService.findAll().forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
