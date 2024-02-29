package com.example.demo.exception;

public class DuplicateIdException extends RepositoryException{
    public DuplicateIdException(String message){
        super(message);
    }
}
