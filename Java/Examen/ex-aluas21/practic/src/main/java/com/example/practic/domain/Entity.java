package com.example.practic.domain;

public class Entity {
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
