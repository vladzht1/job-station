package edu.rut_miit.job_station.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity {
    private String name;
    private String value;

    public Contact(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "value")
    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
