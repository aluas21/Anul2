package domain;

import java.util.Objects;

public class Pacient extends Entity {
    private String name;
    private String surname;
    private int age;

    Pacient(){

    }

    @Override
    public String toString() {
        return "Pacient{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    public Pacient(int id){
        super(id);
    }

    public Pacient(int id, String name, String surname, int age){
        super(id);
        this.age = age;
        this.surname = surname;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) { this.age = age;}

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pacient pacient = (Pacient) o;
        return age == pacient.age && Objects.equals(name, pacient.name) && Objects.equals(surname, pacient.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age);
    }
}
