package com.example;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

        // 페이징 처리
        Pageable pageable = new PageRequest(0, 3); // (가져올 페이지 수, 한 페이지가 포함하는 데이터 수)
        // Page<Customer> page = customerRepository.findAll(pageable);
        Page<Customer> page = customerRepository.findAllOrderByName(pageable);
        System.out.println("한 페이지당 데이터 수 = " + page.getSize());
        System.out.println("현재 페이지 = " + page.getNumber()); // 페이지는 0부터 시작
        System.out.println("전체 페이지 수 = " + page.getTotalPages());
        System.out.println("전체 데이터 수 = " + page.getTotalElements());
        page.getContent().forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
