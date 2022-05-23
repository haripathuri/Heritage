package com.heritage.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Hari Pathuri
 * 5/18/2022 11:46 AM
 */
@Target({ANNOTATION_TYPE, METHOD, FIELD, TYPE, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = AccountTypeValidatorImpl.class)
public @interface AccountTypeValidator {

    String message() default "{AccessTokenValidator," + " message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
