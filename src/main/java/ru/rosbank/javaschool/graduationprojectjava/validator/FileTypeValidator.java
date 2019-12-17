package ru.rosbank.javaschool.graduationprojectjava.validator;

import ru.rosbank.javaschool.graduationprojectjava.constrain.FileType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileTypeValidator implements ConstraintValidator<FileType, String> {
    private String[] extensions;

    @Override
    public void initialize(FileType constraintAnnotation) {
        extensions = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        if (value.isEmpty()) {
            return true;
        }
        return Arrays.stream(extensions).anyMatch(value::endsWith);
    }
}
