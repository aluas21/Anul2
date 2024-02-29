package com.example.examenp3.repository;

import com.example.examenp3.domain.Entity;
import com.example.examenp3.exception.DuplicateIdException;
import com.example.examenp3.exception.NotFoundId;
import com.example.examenp3.exception.RepositoryException;

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
