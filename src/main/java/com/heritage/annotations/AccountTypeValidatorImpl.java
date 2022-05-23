package com.heritage.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.heritage.util.Heritageconstants.CURRENT;
import static com.heritage.util.Heritageconstants.SAVINGS;

/**
 * @author Hari Pathuri
 * 5/18/2022 12:10 PM
 */
public class AccountTypeValidatorImpl implements ConstraintValidator<AccountTypeValidator, String> {
    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();

        if (value.equalsIgnoreCase(SAVINGS) || value.equalsIgnoreCase(CURRENT)) {
            return true;
        }
        context.buildConstraintViolationWithTemplate("Invalid Account Type").
                addPropertyNode("accountType").addConstraintViolation();
        return false;

    }

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(AccountTypeValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
