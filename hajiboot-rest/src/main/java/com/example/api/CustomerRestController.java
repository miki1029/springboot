package com.example.api;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Created by miki on 15. 10. 15..
 */
@RestController
@RequestMapping("api/customers")
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    Page<Customer> getCustomers(@PageableDefault Pageable pageable) { // page(기본값 0), size(기본값 20)을 쿼리스트링으로 받음
        Page<Customer> customers = customerService.findAll(pageable);
        return customers;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Customer getCustomer(@PathVariable Integer id) {
        Customer customer = customerService.findOne(id);
        return customer;
    }

    // 신규 고객 정보 작성
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED) // 없으면 : 200 OK, CREATED : 201 CREATED
    ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, // HTTP Request를 @RequestBody 객체로 받음
                                           UriComponentsBuilder uriBuilder) { // 컨텍스트 상대 경로 URI를 쉽게 만들어 줌
        Customer created = customerService.create(customer);
        URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
        HttpHeaders headers = new HttpHeaders(); // 응답 헤더
        headers.setLocation(location);
        return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
    }

    // 고객 한 명의 정보 업데이트
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setId(id);
        return customerService.update(customer);
    }

    // 고객 한 명의 정보 삭제
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT) // NO_CONTENT : 204 NO_CONTENT
    void deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
    }
}
