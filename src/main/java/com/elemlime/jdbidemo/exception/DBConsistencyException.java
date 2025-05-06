package com.elemlime.jdbidemo.exception;

public class DBConsistencyException extends RuntimeException {
  public DBConsistencyException(String message) {
    super(message);
  }
}
