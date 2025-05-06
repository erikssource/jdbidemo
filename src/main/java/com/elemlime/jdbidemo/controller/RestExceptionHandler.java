package com.elemlime.jdbidemo.controller;

import com.elemlime.jdbidemo.exception.DBConsistencyException;
import com.elemlime.jdbidemo.exception.RowNotFoundException;
import com.elemlime.jdbidemo.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.JdbiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(RowNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleRowNotFoundException(RowNotFoundException ex, WebRequest request) {
    log.debug("Not Found Exception: {}", ex.getMessage());
    return response(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(DBConsistencyException.class)
  public ResponseEntity<ErrorResponse> handleDBConsistencyException(DBConsistencyException ex, WebRequest request) {
    log.error("DBConsistencyException: {}", ex.getMessage());
    return response(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  @ExceptionHandler(JdbiException.class)
  public ResponseEntity<ErrorResponse> handleJdbiException(JdbiException ex, WebRequest request) {
    if (ex.getMessage().toLowerCase().contains("duplicate key")) {
      log.debug("Conflict Exception: {}", ex.getMessage());
      return response(HttpStatus.CONFLICT, "Duplicate value");
    }
    log.error("JdbiException: {}", ex.getMessage());
    return response(HttpStatus.INTERNAL_SERVER_ERROR, "Database error");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
    log.error("Unspecified internal error: {}", ex.getMessage(), ex);
    return response(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
  }

  private ResponseEntity<ErrorResponse> response(HttpStatus status, String message) {
    return new ResponseEntity<>(new ErrorResponse(message), status);
  }
}
