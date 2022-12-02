package com.ortegafran.peoplemanager.Model.Entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="residency")
public class Residency {

    @Id
    @Column(name ="id", columnDefinition = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid4")
    private UUID id;

    @Column(name ="street")
    private String street;

    @Column(name ="bt_street_1")
    private String between_street_1;

    @Column(name ="bt_street_2")
    private String between_street_2;

    @Column(name="num")
    private int numeration;

    @Column(name="orientation")
    private String orientation;

    @Column(name="extra_comment")
    private String comment;

    @OneToOne(mappedBy = "residency")
    private Person person;

    public Residency(){
        this.id = UUID.randomUUID();
    }

    public Residency(String street, String between_street_1, String between_street_2, int numeration, String orientation, String comment) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.between_street_1 = between_street_1;
        this.between_street_2 = between_street_2;
        this.numeration = numeration;
        this.orientation = orientation;
        this.comment = comment;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBetween_street_1() {
        return between_street_1;
    }

    public void setBetween_street_1(String st) {
        this.between_street_1 = st;
    }

    public String getBetween_street_2() {
        return between_street_2;
    }

    public void setBetween_street_2(String st2) {
        this.between_street_2 = st2;
    }

    public int getNumeration() {
        return numeration;
    }

    public void setNumeration(int numeration) {
        this.numeration = numeration;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Residency{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", between_street_1='" + between_street_1 + '\'' +
                ", between_street_2='" + between_street_2 + '\'' +
                ", numeration=" + numeration +
                ", orientation='" + orientation + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Residency residency = (Residency) o;
        return numeration == residency.numeration
                && id.equals(residency.id)
                && street.equals(residency.street)
                && Objects.equals(between_street_1, residency.between_street_1)
                && Objects.equals(between_street_2, residency.between_street_2)
                && orientation.equals(residency.orientation)
                && Objects.equals(comment, residency.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, between_street_1, between_street_2, numeration, orientation, comment);
    }
}
