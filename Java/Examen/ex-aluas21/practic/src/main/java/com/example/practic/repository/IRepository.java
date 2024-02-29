package com.example.practic.repository;


import com.example.practic.domain.Entity;
import com.example.practic.exception.RepositoryException;

import java.util.ArrayList;

public interface IRepository <T extends Entity> extends Iterable<T>{
    public int getSize();

    public T getAt(int pos);

    public Boolean findByID(int id);

    public T getById(int id);

    public void deleteById(int id) throws RepositoryException;

    public void addEntity(T elem) throws RepositoryException;

    public void update(T elem) throws RepositoryException;

    public ArrayList<T> getAll();

}
