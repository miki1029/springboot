package com.example;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by miki on 15. 10. 10..
 */
@EnableAutoConfiguration
@ComponentScan // @Configuration도 스캔 대상
public class App implements CommandLineRunner {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public void run(String... strings) throws Exception {
        // 데이터 추가
        Customer created = customerRepository.save(new Customer(
                null, "민우", "김"));
        System.out.println(created + " is created!");

        // 데이터 표시
        // customerRepository.findAll().forEach((x) -> {System.out.println(x);});
        customerRepository.findAll().forEach(System.out::println); // method reference
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
