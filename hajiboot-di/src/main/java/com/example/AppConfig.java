package com.example;

import com.example.app.AddCalculator;
import com.example.app.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by miki on 15. 10. 10..
 */
@Configuration // JavaConfig 용 클래스임을 알림
public class AppConfig
{
    @Bean
    Calculator calculator() {
        return new AddCalculator();
    }
}
