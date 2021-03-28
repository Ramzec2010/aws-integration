package com.roman.awsintegration.exception.handler;

import com.roman.awsintegration.exception.CategoryNotFoundException;
import com.roman.awsintegration.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
@RequestMapping(
        produces = {"application/vnd.error+json"}
)
public class AppExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({CategoryNotFoundException.class})
    public ResponseEntity<Object> categoryNotFound(CategoryNotFoundException e) {
        log.warn(e.getClass().getCanonicalName() + e.getMessage() + ", 404", e);
        return this.createErrorInternal(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ProductNotFoundException.class})
    public ResponseEntity<Object> productNotFound(ProductNotFoundException e) {
        log.warn(e.getClass().getCanonicalName() + e.getMessage() + ", 404", e);
        return this.createErrorInternal(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    protected ResponseEntity<Object> createErrorInternal(String message, HttpStatus status) {
        return new ResponseEntity<>((new GeneralError()).error(message).status(status.value()).success(Boolean.FALSE), status);
    }
}
