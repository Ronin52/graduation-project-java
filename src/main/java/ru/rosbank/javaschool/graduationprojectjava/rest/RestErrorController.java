package ru.rosbank.javaschool.graduationprojectjava.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import ru.rosbank.javaschool.graduationprojectjava.constants.Errors;
import ru.rosbank.javaschool.graduationprojectjava.dto.ErrorResponseDto;
import ru.rosbank.javaschool.graduationprojectjava.exception.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiIgnore
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class RestErrorController extends AbstractErrorController {
    private final ErrorAttributes errorAttributes;
    private final String path;

    public RestErrorController(ErrorAttributes errorAttributes, @Value("${server.error.path:${error.path:/error}}") String path) {
        super(errorAttributes);
        this.errorAttributes = errorAttributes;
        this.path = path;
    }

    @RequestMapping
    public ResponseEntity<ErrorResponseDto> error(HttpServletRequest request) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Throwable error = errorAttributes.getError(webRequest);
        int status = getStatus(request).value();
        String message = Errors.UNKNOWN;
        if (error == null) {
            return ResponseEntity.status(status).body(
                    new ErrorResponseDto(status, message, Collections.emptyMap())
            );
        }
        if (error instanceof CharacterNotFoundException) {
            status = 404;
            message = Errors.CHARACTER_NOT_FOUND;
            return getErrorDto(error, status, message);
        }
        if (error instanceof ComicsNotFoundException) {
            status = 404;
            message = Errors.COMICS_NOT_FOUND;
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileNotFoundException) {
            status = 404;
            message = Errors.FILE_NOT_FOUND;
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileStorageException) {
            status = 400;
            message = Errors.FILE_CAN_NOT_SAVE;
            return getErrorDto(error, status, message);
        }
        if (error instanceof UnsupportedFileTypeException) {
            status = 400;
            message = Errors.FILE_BAD_TYPE;
            return getErrorDto(error, status, message);
        }
        if (error instanceof ContentTypeIsNullException) {
            status = 400;
            message = Errors.HEADER_CONTENT_TYPE_IS_NULL;
            return getErrorDto(error, status, message);
        }
        if (error instanceof MethodArgumentNotValidException) {
            status = 400;
            message = Errors.VALIDATION;
            final Map<String, List<String>> errors = ((MethodArgumentNotValidException) error).
                    getBindingResult().getFieldErrors().stream()
                    .collect(
                            Collectors.groupingBy(FieldError::getField,
                                    Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage,
                                            Collectors.toList())));
            return getErrorDto(error, status, message, errors);
        }

        return getErrorDto(error, status, message);
    }

    private ResponseEntity<ErrorResponseDto> getErrorDto(Throwable error, int status, String message) {
        error.printStackTrace();
        return ResponseEntity.status(status).body(
                new ErrorResponseDto(status, message, Collections.emptyMap())
        );
    }

    private ResponseEntity<ErrorResponseDto> getErrorDto(Throwable error, int status, String message, Map<String, List<String>> errors) {
        error.printStackTrace();
        return ResponseEntity.status(status).body(
                new ErrorResponseDto(status, message, errors)
        );
    }

    @Override
    public String getErrorPath() {
        return path;
    }
}
