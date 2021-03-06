package com.example.web;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by miki on 15. 10. 15..
 */
@Controller // 변경될 화면 이름을 반환함 (classpath:templates/ + '뷰 이름' + .html
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    /*
     * @RequestMapping 메소드보다 먼저 실행되어 반환 값이 Model에 자동 추가됨
     * model.addAttribute(new CustomerForm())
     * addAttribute()에서 속성 이름을 생략하면 클래스 이름에서 첫 문자를 소문자로 쓴 문자열이 사용(여기서는 customerForm)
     */
    @ModelAttribute
    CustomerForm setUpForm() {
        return new CustomerForm();
    }

    // 고객 목록 화면(customers/list.html)
    // GET /customers
    @RequestMapping(method = RequestMethod.GET)
    String list(Model model) { // Model : 화면에 값을 넘겨주기 위한 용도
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers/list"; // classpath:templates/customers/list.html
    }

    /*
     * @Validated : Bean Validation을 적용하여 그 결과가 BindingResult 인자에 저장된다.
     * 만약 RestController라면 @RequestBody가 붙은 인자에 @Validated 애너테이션을 붙인다.
     * 이 때, BindingResult는 필요없으며 클래스가 Bean Validation 계열 애너테이션이 있어야 한다.
     */
    // 새로운 고객 생성
    // POST /customers/create
    @RequestMapping(value = "create", method = RequestMethod.POST)
    String create(@Validated CustomerForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return list(model); // 오류가 있으면 다시 목록 화면을 표시함
        }
        Customer customer = new Customer();
        // CustomerForm을 Customer에 복사(필드 이름과 타입이 같을 때만 사용 가능)
        // 더 유연한 Bean 변한을 구현하려면 Dozer나 ModelMapper를 이용
        BeanUtils.copyProperties(form, customer);
        customerService.create(customer);
        return "redirect:/customers"; // 목록 화면으로 리다이렉트 'redirect: 변경할 경로'
    }

    // 고객 정보 수정 화면(customers/edit.html)
    // GET /customers/edit?form&id={id}
    @RequestMapping(value = "edit", params = "form", method = RequestMethod.GET) // params : 파라미터에 params가 있을 경우
    String editForm(@RequestParam Integer id, CustomerForm form) { // @RequestParam : 특정 요청 파라미터 매핑
        Customer customer = customerService.findOne(id);
        // Customer를 CustomerForm에 복사 -> @ModelAttribute이므로 화면에 표시됨
        BeanUtils.copyProperties(customer, form);
        return "customers/edit";
    }

    // 고객 정보 수정
    // POST /customers/edit
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    String edit(@RequestParam Integer id, @Validated CustomerForm form, BindingResult result) {
        if(result.hasErrors()) {
            return editForm(id, form);
        }
        Customer customer = new Customer();
        BeanUtils.copyProperties(form, customer);
        customer.setId(id);
        customerService.update(customer);
        return "redirect:/customers";
    }

    // 고객 목록 화면으로 리다이렉트
    // GET /customers/edit?goToTop
    @RequestMapping(value = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/customers";
    }

    // 고객 정보 삭제
    // POST /customers/delete
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    String delete(@RequestParam Integer id) {
        customerService.delete(id);
        return "redirect:/customers";
    }
}
