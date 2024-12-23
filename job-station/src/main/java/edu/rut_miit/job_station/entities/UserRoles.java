package edu.rut_miit.job_station.entities;

public enum UserRoles {
    USER(0),
    ADMIN(100);

    private int value;

    UserRoles(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static UserRoles of(int value) {
        return switch (value) {
            case 100 -> UserRoles.ADMIN;
            default -> UserRoles.USER;
        };
    }
}
