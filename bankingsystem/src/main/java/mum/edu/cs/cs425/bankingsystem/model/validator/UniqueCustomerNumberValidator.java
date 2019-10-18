package mum.edu.cs.cs425.bankingsystem.model.validator;

import mum.edu.cs.cs425.bankingsystem.service.AccountService;
import mum.edu.cs.cs425.bankingsystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCustomerNumberValidator implements ConstraintValidator<UniqueCustomerNumber, Long> {

    private CustomerService customerService;

    public UniqueCustomerNumberValidator() {
    }

    @Autowired
    public UniqueCustomerNumberValidator(CustomerService customerService) {
        this.customerService = customerService;
    }

    public boolean isValid(Long customerNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid;
        if(customerService != null) {
            valid = (customerNumber > 0 && !customerService.getCustomerByCustomerNumber(customerNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
