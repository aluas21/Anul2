package repository;

import domain.Entity;
import exception.DuplicateIdException;
import exception.NotFoundId;

import java.util.ArrayList;

/*
TO DO:
- de facut indexare

 */
public class Repository <T extends Entity>{
    ArrayList<T> elems = new ArrayList<T>();

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

    public void deleteById(int id) throws NotFoundId{
        if(!findByID(id))
            throw new NotFoundId("Id-ul nu a fost gasit");
        elems.remove(getById(id));
    }

    public void addEntity(T elem) throws DuplicateIdException{
        if(findByID(elem.getId()))
            throw new DuplicateIdException("Id duplicat "+ String.valueOf(elem.getId()));
        elems.add(elem);
    }

    public void update(T elem) throws NotFoundId{
        if(!findByID(elem.getId()))
            throw new NotFoundId("ID-ul nu a fost gasit");
        for(int i  = 0;i<elems.size();i++)
            if(elems.get(i).getId() == elem.getId())
                elems.set(i, elem);
    }

    public ArrayList<T> getAll(){
        return elems;
    }

}
