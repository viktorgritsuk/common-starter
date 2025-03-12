package com.common.starter.validation.matchenum;

import java.util.StringJoiner;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;

import com.common.starter.model.enums.StringInterpretedEnum;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class MatchEnumValidator implements ConstraintValidator<MatchEnum, String> {

    /**
     * Class of Enum.
     */
    private Class<? extends Enum> enumClass;

    /**
     * Is enum implement {@link StringInterpretedEnum} interface.
     */
    private boolean isStringInterpretedEnum;

    @Override
    public void initialize(MatchEnum constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();

        isStringInterpretedEnum = StringInterpretedEnum.class.isAssignableFrom(enumClass);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Function<Enum<?>, Boolean> checkValue;
        StringJoiner errorStringJoiner = new StringJoiner(", ");

        if (isStringInterpretedEnum) {
            checkValue = enumValue -> handleStringInterpretedEnum(errorStringJoiner, enumValue, value);
        }
        else {
            checkValue = enumValue -> handleDefaultEnum(errorStringJoiner, enumValue, value);
        }

        for (Enum<?> enumConstant : enumClass.getEnumConstants()) {
            if (Boolean.TRUE.equals(checkValue.apply(enumConstant))) {
                return true;
            }
        }

        if (context instanceof HibernateConstraintValidatorContext) {
            context.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter("value", errorStringJoiner.toString());
        }

        return false;
    }

    private boolean handleStringInterpretedEnum(
        final StringJoiner errorStringJoiner,
        final Enum<?> enumValue,
        final String validatingValue
    ) {
        String stringInterpretedValue = ((StringInterpretedEnum) enumValue).getStringInterpretedValue();

        errorStringJoiner.add(StringUtils.wrap(stringInterpretedValue, "'"));

        return stringInterpretedValue.equals(validatingValue);
    }

    private boolean handleDefaultEnum(
        final StringJoiner errorStringJoiner,
        final Enum<?> enumValue,
        final String validatingValue
    ) {
        errorStringJoiner.add(StringUtils.wrap(enumValue.toString(), "'"));

        return enumValue.name().equals(validatingValue);
    }

}
