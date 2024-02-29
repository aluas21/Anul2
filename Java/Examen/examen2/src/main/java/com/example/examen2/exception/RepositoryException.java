package com.example.examen2.exception;

public class RepositoryException extends Exception{
    public RepositoryException(String message){
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
