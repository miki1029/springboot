package com.example.web;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by miki on 15. 10. 15..
 */
@Controller // 변경될 화면 이름을 반환함 (classpath:templates/ + '뷰 이름' + .html
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) { // Model : 화면에 값을 넘겨주기 위한 용도
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list"; // classpath:templates/customers/list.html
    }
}
