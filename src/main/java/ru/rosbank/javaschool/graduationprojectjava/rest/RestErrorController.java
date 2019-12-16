package ru.rosbank.javaschool.graduationprojectjava.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import ru.rosbank.javaschool.graduationprojectjava.dto.ErrorResponseDto;
import ru.rosbank.javaschool.graduationprojectjava.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;

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
        String message = "error.unknown";
        if (error == null) {
            return ResponseEntity.status(status).body(
                    new ErrorResponseDto(status, message)
            );
        }
        if (error instanceof CharacterNotFoundException) {
            status = 404;
            message = "error.character.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof ComicsNotFoundException) {
            status = 404;
            message = "error.comics.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileNotFoundException) {
            status = 404;
            message = "error.file.not_found";
            return getErrorDto(error, status, message);
        }
        if (error instanceof FileStorageException) {
            status = 400;
            message = "error.file.can_not_save";
            return getErrorDto(error, status, message);
        }
        if (error instanceof UnsupportedFileTypeException) {
            status = 400;
            message = "error.file.bad_type";
            return getErrorDto(error, status, message);
        }
        if (error instanceof ContentTypeIsNullException){
            status = 400;
            message = "error.header.content_type_is_null";
            return getErrorDto(error, status, message);
        }

        return getErrorDto(error, status, message);
    }

    private ResponseEntity<ErrorResponseDto> getErrorDto(Throwable error, int status, String message) {
        error.printStackTrace();
        return ResponseEntity.status(status).body(
                new ErrorResponseDto(status, message)
        );
    }

    @Override
    public String getErrorPath() {
        return path;
    }
}
