package com.example.examen2.exception;

public class DuplicateIdException extends RepositoryException{
    public DuplicateIdException(String message){
        super(message);
    }
}
