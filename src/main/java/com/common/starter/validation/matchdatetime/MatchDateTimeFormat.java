package com.common.starter.validation.matchdatetime;


import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = MatchDateTimeFormatValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Repeatable(MatchDateTimeFormat.List.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchDateTimeFormat {

    /**
     * Date time format for validation string values.
     *
     * @return date time format
     */
    String format();

    /**
     * Should validated values be not empty.
     *
     * @return Should validated values be not empty.
     */
    boolean notEmpty() default true;

    /**
     * The error message template(from validation.properties).
     *
     * @return the error message template
     */
    String message() default "{validation.MatchDateTimeFormat.message}";

    /**
     * The groups the constraint belongs to.
     *
     * @return the groups the constraint belongs to.
     */
    Class<?>[] groups() default {};

    /**
     * The payload associated to the constraint.
     *
     * @return the payload associated to the constraint.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link MatchDateTimeFormat} annotations on the same element.
     *
     * @see MatchDateTimeFormat
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        /**
         * Several {@link MatchDateTimeFormat} annotations on the same element.
         *
         * @return several {@link MatchDateTimeFormat} annotations on the same element
         */
        MatchDateTimeFormat[] value();
    }

}
