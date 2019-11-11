package com.smt.example.service.utilities;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * Validation Service @author Turuu
 */

@Component
public class ValidationService {

    /**
     * do.Validate.Password
     **/

    public boolean doValidatePassword(String password) {
        return StringUtils.isNotBlank(password) && password.length() > 8;
    }

}