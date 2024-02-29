package com.example.demo.repository;

import com.example.demo.domain.Entity;
import com.example.demo.exception.DuplicateIdException;
import com.example.demo.exception.NotFoundId;
import com.example.demo.exception.RepositoryException;

import java.util.ArrayList;

public class Repository <T extends Entity> extends AbstractRepository<T>{

    public int getSize(){
        return elems.size();
    }

    public T getAt(int pos){
        return elems.get(pos);
    }

    public Boolean findByID(int id){
        for(T elem : elems)
            if(elem.getId() == id)
                return true;
        return false;
    }

    public T getById(int id){
        for(T elem: elems)
            if(elem.getId() == id)
                return elem;
        return null;
    }

    public void deleteById(int id) throws RepositoryException {
        if(!findByID(id))
            throw new NotFoundId("Id-ul nu a fost gasit");
        elems.remove(getById(id));
    }

    public void addEntity(T elem) throws RepositoryException {
        if(findByID(elem.getId()))
            throw new DuplicateIdException("Id duplicat "+ String.valueOf(elem.getId()));
        elems.add(elem);
    }

    public void update(T elem) throws RepositoryException {
        if(!findByID(elem.getId()))
            throw new NotFoundId("ID-ul nu a fost gasit");
        for(int i  = 0;i<elems.size();i++)
            if(elems.get(i).getId() == elem.getId())
                elems.set(i, elem);
    }

    public ArrayList<T> getAll(){
        return new ArrayList<>(elems);
    }

}
