package repository;

import Domain.BMI;
import Domain.HealthData;


import java.util.ArrayList;

public class Repository <T extends HealthData>{
    ArrayList<T> elems = new ArrayList<T>();

    public int getSize(){
        return elems.size();
    }

    public T getAt(int pos){
        return elems.get(pos);
    }

//    public void deleteById(int id) throws RepositoryException {
//        if(!findByID(id))
//            throw new NotFoundId("Id-ul nu a fost gasit");
//        elems.remove(getById(id));
//    }

    public void addEntity(T elem) {
        elems.add(elem);
    }

//    public void update(T elem) {
//        for(int i  = 0;i<elems.size();i++)
//            if(elems.get(i).getId() == elem.getId())
//                elems.set(i, elem);
//    }

    public ArrayList<T> getAll(){
        return new ArrayList<>(elems);
    }

}
