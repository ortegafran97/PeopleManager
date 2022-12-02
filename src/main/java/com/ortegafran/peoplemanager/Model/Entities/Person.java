package com.ortegafran.peoplemanager.Model.Entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "people")
@Data
@AllArgsConstructor
public class Person {

    @Id
    @Column(name = "id_people",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name="dni")
    private String dni;

    @Column(name = "name")
    private String name;

    @Column(name = "firstlastname")
    private String firstLastName;

    @Column(name = "secondlastname")
    private String secondLastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "residency_id", referencedColumnName = "id", insertable = true, updatable = true)
    private Residency residency;

    @OneToMany(mappedBy = "person")
    private List<JobRecord> jobsRecord;

    public Person(){
        id = UUID.randomUUID();
    }

    public Person(UUID id, String dni, String name, String firstLastName, String secondLastName,Residency residency) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.residency = residency;
    }

    public Person(String dni, String name, String firstLastName, String secondLastName, Residency residency) {
        this.id = UUID.randomUUID();
        this.dni = dni;
        this.name = name;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.residency = residency;
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

    public Residency getResidency() {
        return residency;
    }

    public void setResidency(Residency residency) {
        this.residency = residency;
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
        return Objects.equals(id, person.id) && dni.equals(person.dni) && name.equals(person.name) && firstLastName.equals(person.firstLastName) && Objects.equals(secondLastName, person.secondLastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name, firstLastName, secondLastName);
    }
}
