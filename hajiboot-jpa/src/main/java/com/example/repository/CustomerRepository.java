package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by miki on 15. 10. 12..
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
