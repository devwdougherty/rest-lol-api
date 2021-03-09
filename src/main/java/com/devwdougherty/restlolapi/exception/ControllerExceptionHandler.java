package com.devwdougherty.restlolapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                ex.getMessage(),
                new Date(),
                responseStatus != null ? responseStatus.value().value() : HttpStatus.BAD_REQUEST.value(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        logger.error("MethodArgumentNotValidException threw: " + exceptionMessage.toString());

        return ResponseEntity.status(responseStatus != null ? responseStatus.value() : HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }

    /**
     * A general handler for all uncaught exceptions. {@link com.devwdougherty.restlolapi.exception.ExceptionMessage}
     *
     * @return ExceptionMessage object.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleAllExceptions(Exception ex, WebRequest request) {

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                ex.getMessage(),
                new Date(),
                responseStatus != null ? responseStatus.value().value() : HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        logger.error("Exception threw: " + exceptionMessage.toString());

        return ResponseEntity.status(responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionMessage);
    }

    /**
     * Custom exception for 404 requests {@link com.devwdougherty.restlolapi.exception.ExceptionMessage}
     *
     * @return ExceptionMessage object.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                ex.getMessage(),
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                ex.getClass().getSimpleName(),
                webRequest.getDescription(false)
        );

        logger.error("ResourceNotFoundException threw: " + exceptionMessage.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionMessage);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        ExceptionMessage exceptionMessage = new ExceptionMessage(
                ex.getMessage(),
                new Date(),
                responseStatus != null ? responseStatus.value().value() : HttpStatus.BAD_REQUEST.value(),
                ex.getClass().getSimpleName(),
                request.getDescription(false)
        );

        logger.error("ConstraintViolationException threw: " + exceptionMessage.toString());

        return ResponseEntity.status(responseStatus != null ? responseStatus.value() : HttpStatus.BAD_REQUEST).body(exceptionMessage);
    }
}
