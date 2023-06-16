package com.quathar.api.application.controller;

import com.quathar.api.data.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <h1>Global Controller Advice</h1>
 *
 * @since 2023-06-09
 * @version 1.0
 * @author Q
 */
@RestControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Catches ResourceNotFoundExceptions and handles them.
     *
     * @return not found response entity
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException() {
        return ResponseEntity.notFound()
                             .build();
    }

}
