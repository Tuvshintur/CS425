package mum.edu.cs.cs425.gdm.model.validator;

import mum.edu.cs.cs425.gdm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueProductNumberValidator implements ConstraintValidator<UniqueProductNumber, Long> {

    private ProductService productService;

    public UniqueProductNumberValidator() {
    }

    @Autowired
    public UniqueProductNumberValidator(ProductService productService) {
        this.productService = productService;
    }

    public boolean isValid(Long productNumber, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid;
        if(productService != null) {
            valid = (productNumber > 0 && !productService.findByProductNumber(productNumber).isPresent());
        } else {
            valid = true;
        }
        return valid;
    }
}
