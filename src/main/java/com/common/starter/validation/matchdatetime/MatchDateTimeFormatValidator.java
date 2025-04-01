package com.common.starter.validation.matchdatetime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class MatchDateTimeFormatValidator implements ConstraintValidator<MatchDateTimeFormat, String> {

    /**
     * Validation date time format.
     */
    private String format;

    /**
     * Should validated value be not empty.
     */
    private boolean notEmpty;

    /**
     * Date time formatter.
     */
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public void initialize(MatchDateTimeFormat constraintAnnotation) {
        format = constraintAnnotation.format();
        notEmpty = constraintAnnotation.notEmpty();
        dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!notEmpty && StringUtils.isEmpty(value)) {
            return true;
        }

        try {
            dateTimeFormatter.parse(value);
        }
        catch (DateTimeParseException e) {
            if (context instanceof HibernateConstraintValidatorContext) {
                context.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter("format", this.format);
            }
            return false;
        }

        return true;
    }

}
