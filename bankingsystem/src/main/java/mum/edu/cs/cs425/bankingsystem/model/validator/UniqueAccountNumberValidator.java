package mum.edu.cs.cs425.bankingsystem.model.validator;

import mum.edu.cs.cs425.bankingsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueAccountNumberValidator implements ConstraintValidator<UniqueAccountNumber, Long> {

    private AccountService accountService;

    public UniqueAccountNumberValidator() {
    }

    @Autowired
    public UniqueAccountNumberValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean isValid(Long accountNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid;
        if(accountService != null) {
            valid = (accountNumber > 0 && !accountService.getAccountByAccountNumber(accountNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
