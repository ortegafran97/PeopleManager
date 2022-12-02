package com.ortegafran.peoplemanager.Model.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="job")
@Data
@AllArgsConstructor
public class JobRecord {
    @Id
    @Column(name="id_job",columnDefinition = "uuid")
    @GeneratedValue
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id = UUID.randomUUID();
    private String role;
    private String enterprise;
    @Column(name="start_date", nullable = false)
    private LocalDateTime start;
    @Column(name="end_date")
    private LocalDateTime end;

    @ManyToOne
    @JoinColumn(name="person_id",nullable = false)
    private Person person;

    public JobRecord(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    @Override
    public String toString() {
        return "JobRecord{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobRecord jobRecord = (JobRecord) o;
        return Objects.equals(id, jobRecord.id) && Objects.equals(role, jobRecord.role) && Objects.equals(enterprise, jobRecord.enterprise) && Objects.equals(start, jobRecord.start) && Objects.equals(end, jobRecord.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, enterprise, start, end);
    }
}
