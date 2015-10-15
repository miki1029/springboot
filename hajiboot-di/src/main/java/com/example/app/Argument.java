package com.example.app;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Created by miki on 15. 10. 10..
 */
@Data // getter, setter(final인 경우 제외), toString(), equals(), hashCode()
@RequiredArgsConstructor
public class Argument {
    private final int a;
    private final int b;
}
