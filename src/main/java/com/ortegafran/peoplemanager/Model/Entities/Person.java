package com.ortegafran.peoplemanager.Model.Entities;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id= UUID.randomUUID();

    @Column(name="dni")
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "firstLastName")
    private String firstLastName;

    @Column(name = "secondLastName")
    private String secondLastName;

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
}
