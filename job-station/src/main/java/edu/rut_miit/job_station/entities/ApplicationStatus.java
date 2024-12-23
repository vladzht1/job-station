package edu.rut_miit.job_station.entities;

public enum ApplicationStatus {
    UNSEEN(0),
    SEEN(1),
    INVITE(2),
    REJECT(3);

    private int code;

    private ApplicationStatus(int code) {
        this.code = code;
    }

    public int value() {
        return code;
    }
}
