package me.progresssoft.interview.exeption;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Husam Aljamal
 * @email m-husam@hotmail.com
 * @since 20 Nov, 2021
 * @details https://github.com/mhusam/Spring-Boot-AngluarJS-Docker
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TwiceRecordException.class)
    protected ResponseEntity<ApiException.Response> handleConflict(TwiceRecordException ex, WebRequest request) {
        return buildResponseEntity(new ApiException(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex));
    }

    private ResponseEntity<ApiException.Response> buildResponseEntity(ApiException apiException) {
        return new ResponseEntity<>(apiException.getResponse(), apiException.getStatus());
    }

}
