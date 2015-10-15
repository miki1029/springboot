package com.example.app;

/**
 * Created by miki on 15. 10. 10..
 */
public class Frontend {
    ArgumentResolver argumentResolver;
    Calculator calculator;

    public void setArgumentResolver(ArgumentResolver argumentResolver) {
        this.argumentResolver = argumentResolver;
    }

    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    public void run() {
        System.out.print("Enter 2 numbers like 'a b' : ");
        Argument argument = argumentResolver.resolve(System.in);
        int result = calculator.calc(argument.getA(), argument.getB());
        System.out.println("result = " + result);
    }
}
