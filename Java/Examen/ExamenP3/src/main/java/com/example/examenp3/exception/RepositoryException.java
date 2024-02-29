package com.example.examenp3.exception;

public class RepositoryException extends Exception{
    public RepositoryException(String message){
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
