package com.example.examen2.domain;

public interface EntityConvert<T extends Entity>{
    public String ToString(T elem);
    public T fromString(String line);
}
