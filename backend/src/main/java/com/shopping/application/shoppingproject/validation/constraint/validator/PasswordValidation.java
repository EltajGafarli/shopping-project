package com.shopping.application.shoppingproject.validation.constraint.validator;


import com.shopping.application.shoppingproject.validation.constraint.PasswordConstraint;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static com.shopping.application.shoppingproject.util.ValidationConstants.PASSWORD_IS_NOT_VALID;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraint.class)
@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PasswordValidation {

    String message() default PASSWORD_IS_NOT_VALID;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}