package mum.edu.cs.cs425.bankingsystem.model.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCustomerNumberValidator.class)
public @interface UniqueCustomerNumber {

    String message() default "{edu.mum.cs.cs425.customvalidators.uniquecustomernumber.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
