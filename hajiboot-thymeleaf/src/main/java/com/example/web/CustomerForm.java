package com.example.web;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by miki on 15. 10. 16..
 */
@Data
public class CustomerForm {
    // Bean Validation
    @NotNull
    @Size(min = 1, max = 127)
    private String firstName;

    @NotNull
    @Size(min = 1, max = 127)
    private String lastName;
}
