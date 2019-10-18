package mum.edu.cs.cs425.bankingsystem.controller;

import mum.edu.cs.cs425.bankingsystem.model.Customer;
import mum.edu.cs.cs425.bankingsystem.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = "/secured/customer")
    public ModelAndView customer(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customers", customerService.getAllCustomersPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/customer/index");
        return modelAndView;
    }

    @GetMapping(value = "/secured/customer/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "secured/customer/add";
    }

    @PostMapping(value = "/secured/customer/add")
    public String addCustomer(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/customer/add";
        }

        customer = customerService.saveCustomer(customer);
        return "redirect:/secured/customer";
    }

    @GetMapping(value = "/secured/customer/search")
    public ModelAndView searchCustomer(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customers", customerService.getCustomersByFirstName(pageNo, searchString));
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.setViewName("secured/customer/index");
        return modelAndView;
    }

}
