package com.example.app;

import java.io.InputStream;

/**
 * Created by miki on 15. 10. 10..
 */
public interface ArgumentResolver {
    Argument resolve(InputStream stream);
}
