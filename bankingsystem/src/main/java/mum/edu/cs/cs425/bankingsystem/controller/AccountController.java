package mum.edu.cs.cs425.bankingsystem.controller;

import mum.edu.cs.cs425.bankingsystem.model.Account;
import mum.edu.cs.cs425.bankingsystem.service.AccountService;
import mum.edu.cs.cs425.bankingsystem.service.AccountTypeService;
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
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;
    private AccountTypeService accountTypeService;

    public AccountController(AccountService accountService, CustomerService customerService, AccountTypeService accountTypeService) {
        this.accountService = accountService;
        this.customerService = customerService;
        this.accountTypeService = accountTypeService;
    }

    @GetMapping(value = "/secured/account")
    public ModelAndView account(@RequestParam(defaultValue = "0") int pageNo) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountService.getAllAccountsPaged(pageNo));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.addObject("netLiquidity", accountService.computeNetLiquidity());
        modelAndView.setViewName("secured/account/index");
        return modelAndView;
    }

    @GetMapping(value = "/secured/account/add")
    public String addAccountForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("accountTypes", accountTypeService.getAllAccountTypes());
        return "secured/account/add";
    }

    @PostMapping(value = "/secured/account/add")
    public String addAccount(@Valid @ModelAttribute Account account, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "secured/account/add";
        }

        account = accountService.saveAccount(account);

        return "redirect:/secured/account";
    }

    @GetMapping(value = "/secured/account/search")
    public ModelAndView searchAccount(@RequestParam(defaultValue = "0") int pageNo, @RequestParam String searchString) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("accounts", accountService.getAccountsByCustomer_LastName(pageNo, searchString));
        modelAndView.addObject("currentPageNo", pageNo);
        modelAndView.addObject("searchString", searchString);
        modelAndView.addObject("netLiquidity", accountService.computeNetLiquidity());
        modelAndView.setViewName("secured/account/index");
        return modelAndView;
    }
}
