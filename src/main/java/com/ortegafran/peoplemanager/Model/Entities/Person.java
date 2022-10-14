package com.ortegafran.peoplemanager.Model.Entities;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "people")
public class Person {

    @Id
    @Column(name = "id",columnDefinition = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id = UUID.randomUUID();

    @Column(name="dni")
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "firstlastname")
    private String firstLastName;

    @Column(name = "secondlastname")
    private String secondLastName;

    public Person(){}

    public Person(String dni, String name, String firstLastName, String secondLastName) {
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    public Person(UUID id, String dni, String name, String firstLastName, String secondLastName) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    @Override
    public String toString() {
        return "Person{ " +
                "id='" + id.toString() + '\'' +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return dni.equals(person.dni) && name.equals(person.name) && firstLastName.equals(person.firstLastName) && Objects.equals(secondLastName, person.secondLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, name, firstLastName, secondLastName);
    }
}
