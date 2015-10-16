package com.example.repository;

import com.example.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by miki on 15. 10. 12..
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName") // JPQL
/*    @Query(value =
            "SELECT id, first_name, last_name FROM customers ORDER BY first_name, last_name",
            nativeQuery = true)*/
    List<Customer> findAllOrderByName();

    @Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName") // JPQL
    Page<Customer> findAllOrderByName(Pageable pageable);
}
