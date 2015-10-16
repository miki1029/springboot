package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by miki on 15. 10. 10..
 */
@EnableAutoConfiguration
@ComponentScan // @Configuration도 스캔 대상
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
