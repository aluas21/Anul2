package com.example.examen2.exception;

public class NotFoundId  extends RepositoryException{
    public NotFoundId(String message){
        super(message);
    }
}
