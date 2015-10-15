package com.example;

import com.example.app.Argument;
import com.example.app.ArgumentResolver;
import com.example.app.Calculator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

/**
 * Created by miki on 15. 10. 10..
 */
@EnableAutoConfiguration
@Import(AppConfig.class) // @Configuration이 붙은 클래스
public class App {
    public static void main(String[] args) {
        /*
         * @param App.class @EnableAutoConfiguration이 붙은 클래스
         * @return ApplicationContext DI Container
         * ConfigurableApplicationContext : close()로 닫을 수 있는 타입
         * try-with-resources 구문
         */
        try(ConfigurableApplicationContext context = SpringApplication.run(App.class, args)) {

            System.out.print("Enter 2 numbers like 'a b' : ");
            ArgumentResolver argumentResolver = context.getBean(ArgumentResolver.class);
            Argument argument = argumentResolver.resolve(System.in);
            Calculator calculator = context.getBean(Calculator.class);
            int result = calculator.calc(argument.getA(), argument.getB());
            System.out.println("result = " + result);
        }
    }
}
