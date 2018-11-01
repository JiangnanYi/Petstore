package com.lij.entity;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {}
)
public @interface Status {
    String message() default "{只能是下面三个字符串'placed','approved','delivered'其中之一}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class StatusValidator implements ConstraintValidator<Status, String> {

    @Override
    public void initialize(Status constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Arrays.asList("placed","approved","delivered").contains(s);
    }
}
