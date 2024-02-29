package com.example.practic.exception;

public class DuplicateIdException extends RepositoryException{
    public DuplicateIdException(String message){
        super(message);
    }
}
