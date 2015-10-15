package com.example.app;

/**
 * Created by miki on 15. 10. 10..
 */
public class AddCalculator implements Calculator {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
