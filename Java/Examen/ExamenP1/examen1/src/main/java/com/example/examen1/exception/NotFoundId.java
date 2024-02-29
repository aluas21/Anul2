package com.example.examen1.exception;

public class NotFoundId  extends RepositoryException{
    public NotFoundId(String message){
        super(message);
    }
}
