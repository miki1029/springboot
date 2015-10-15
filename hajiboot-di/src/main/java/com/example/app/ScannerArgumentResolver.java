package com.example.app;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by miki on 15. 10. 10..
 */
public class ScannerArgumentResolver implements ArgumentResolver {
    @Override
    public Argument resolve(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        return new Argument(a, b);
    }
}
