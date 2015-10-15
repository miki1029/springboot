package com.example.app;

import org.springframework.stereotype.Component;

/**
 * Created by miki on 15. 10. 10..
 */
@Component
public class AddCalculator implements Calculator {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
