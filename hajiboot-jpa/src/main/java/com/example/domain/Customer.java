package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by miki on 15. 10. 12..
 */
@Entity                         // JPA Entity
@Table(name = "customers")      // JPA Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id                         // JPA Primay Key
    @GeneratedValue             // JPA DB auto increment
    private Integer id;

    @Column(nullable = false)   // JPA Column and constraint
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
