package ru.rosbank.javaschool.graduationprojectjava.constrain;

import ru.rosbank.javaschool.graduationprojectjava.validator.FileTypeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FileTypeValidator.class)
public @interface FileType {
    String message() default "{javax.validation.constraints.IsImage.message}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    String[] value() default {};
}
