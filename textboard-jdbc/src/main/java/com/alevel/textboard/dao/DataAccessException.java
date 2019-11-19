package com.alevel.textboard.dao;

public class DataAccessException extends RuntimeException {
    public DataAccessException(Throwable cause) {
        super(cause);
    }
    public DataAccessException(String message) {
        super(message);
    }
}
