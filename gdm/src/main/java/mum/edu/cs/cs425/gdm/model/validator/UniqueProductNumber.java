package mum.edu.cs.cs425.gdm.model.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueProductNumberValidator.class)
public @interface UniqueProductNumber {

    String message() default "{edu.mum.cs.cs425.customvalidators.uniqueproductnumber.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
