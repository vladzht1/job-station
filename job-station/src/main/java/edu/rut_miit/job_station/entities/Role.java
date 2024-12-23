package edu.rut_miit.job_station.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    private UserRoles name;

    public Role(UserRoles name) {
        this.name = name;
    }

    protected Role() {}

    public UserRoles getName() {
        return name;
    }

    public void setName(UserRoles name) {
        this.name = name;
    }
}
