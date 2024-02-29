package com.example.examen1.domain;

import java.io.Serializable;

public class Entity implements Serializable {
    private int id;

    public Entity() {
        this.id = 0;
    }

    public Entity(int id) {
        this.id = id;
    }

    public Entity(Entity e) {
        this.id = e.id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
