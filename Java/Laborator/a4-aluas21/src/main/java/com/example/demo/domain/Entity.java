package com.example.demo.domain;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected int id;

    Entity(){
        this.id = -1;
    }

    Entity(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
         this.id = id;
    }
}