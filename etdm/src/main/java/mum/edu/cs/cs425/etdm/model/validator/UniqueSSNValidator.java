package mum.edu.cs.cs425.etdm.model.validator;

import mum.edu.cs.cs425.etdm.service.AthleteService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueSSNValidator implements ConstraintValidator<UniqueSSN, String> {

    private AthleteService athleteService;

    public UniqueSSNValidator() {
    }

    public UniqueSSNValidator(AthleteService athleteService) {
        this.athleteService = athleteService;
    }

    public boolean isValid(String ssn, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid;
        if(athleteService != null) {
            valid = (ssn !=null && !athleteService.getAthleteBySSN(ssn).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
