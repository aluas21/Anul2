package com.example.examenp3.exception;

public class DuplicateIdException extends RepositoryException{
    public DuplicateIdException(String message){
        super(message);
    }
}
