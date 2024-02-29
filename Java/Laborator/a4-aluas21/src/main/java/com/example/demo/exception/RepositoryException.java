package com.example.demo.exception;

public class RepositoryException extends Exception{
    public RepositoryException(String message){
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
