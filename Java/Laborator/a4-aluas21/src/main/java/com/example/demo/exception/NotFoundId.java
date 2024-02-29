package com.example.demo.exception;

public class NotFoundId  extends RepositoryException{
    public NotFoundId(String message){
        super(message);
    }
}
