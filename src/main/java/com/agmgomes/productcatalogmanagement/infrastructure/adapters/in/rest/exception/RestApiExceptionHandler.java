package com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.agmgomes.productcatalogmanagement.domain.catalog.exception.CatalogOwnerNotFoundException;
import com.agmgomes.productcatalogmanagement.domain.category.exception.CategoryNotFoundException;
import com.agmgomes.productcatalogmanagement.domain.product.exception.OwnershipMismatchException;
import com.agmgomes.productcatalogmanagement.domain.product.exception.ProductNotFoundException;
import com.agmgomes.productcatalogmanagement.infrastructure.adapters.in.rest.exception.dto.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestApiExceptionHandler {

        @ExceptionHandler(CategoryNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(CategoryNotFoundException e,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        @ExceptionHandler(CatalogOwnerNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleCatalogOwnerNotFoundException(CatalogOwnerNotFoundException e,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        @ExceptionHandler(OwnershipMismatchException.class)
        public ResponseEntity<ErrorResponse> handleOwnershipMismatchException(OwnershipMismatchException e,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                HttpStatus.BAD_REQUEST.value(),
                                "Ownership Mismatch between category and product.",
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                        HttpServletRequest request) {
                List<Map<String, String>> fieldErrors = e.getBindingResult().getFieldErrors().stream()
                                .map(fieldError -> {
                                        Map<String, String> error = new HashMap<>();
                                        error.put("field", fieldError.getField());
                                        error.put("message", fieldError.getDefaultMessage());
                                        return error;
                                })
                                .collect(Collectors.toList());
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                e.getStatusCode().value(),
                                fieldErrors,
                                "Validation Failed.",
                                request.getRequestURI());

                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        }

        @ExceptionHandler(NoResourceFoundException.class)
        public ResponseEntity<ErrorResponse> handleNoResourceFoundException(NoResourceFoundException e,
                        HttpServletRequest request) {
                ErrorResponse errorResponse = new ErrorResponse(
                                LocalDateTime.now(),
                                e.getStatusCode().value(),
                                "Resource Not Found.",
                                e.getMessage(),
                                request.getRequestURI());

                return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        }

}
