package com.example.examen2.repository;

import com.example.examen2.domain.Entity;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class  AbstractRepository<T extends Entity> implements IRepository<T> {
    ArrayList<T> elems = new ArrayList<T>();

    @Override
    public Iterator<T> iterator() {
        // returnam un iterator pe un shallow copy :) al campului data
        return new ArrayList<T>(elems).iterator();
//        return data.iterator();
    }
}
