package com.example.examen1.exception;

public class DuplicateIdException extends RepositoryException{
    public DuplicateIdException(String message){
        super(message);
    }
}
