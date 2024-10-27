package edu.rut_miit.job_station.entities;

import edu.rut_miit.job_station.exceptions.ClientException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String middleName;
    private String lastName;
    private String login;
    private String password;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
    private boolean isBlocked;
    private String blockReason;
    private Set<Resume> resumes;

    public User(String firstName, String middleName, String lastName, String login, String password) {
        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setLogin(login);
        setPassword(password);
        setBlocked(false);
        setBlockReason(null);
        setResumes(new HashSet<>());

        setJoinedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    protected User() {}

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Column(name = "joined_at")
    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    @Column(name = "updated_at")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Column(name = "is_blocked")
    public boolean isBlocked() {
        return isBlocked;
    }

    @Column(name = "block_reason")
    public String getBlockReason() {
        return blockReason;
    }

    @OneToMany
    public Set<Resume> getResumes() {
        return resumes;
    }

    public void addResume(Resume resume) {
        resumes.add(resume);
    }

    public void block() {
        blockOrThrowException();

        markAtUpdated();
    }

    public void block(String reason) {
        blockOrThrowException();
        setBlockReason(reason);

        markAtUpdated();
    }

    public void unblock() {
        if (!isBlocked()) {
            throw new ClientException.InvalidStateException("Данный пользователь не заблокирован");
        }

        setBlocked(false);
        setBlockReason(null);

        markAtUpdated();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        markAtUpdated();
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
        markAtUpdated();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        markAtUpdated();
    }

    public void setLogin(String login) {
        this.login = login;
        markAtUpdated();
    }

    public void setPassword(String password) {
        this.password = password;
        markAtUpdated();
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
        markAtUpdated();
    }

    protected void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    protected void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    protected void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    protected void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    private void blockOrThrowException() {
        if (isBlocked()) {
            throw new ClientException.InvalidStateException("Данный пользователь уже заблокирован");
        }

        setBlocked(true);
    }

    private void markAtUpdated() {
        setUpdatedAt(LocalDateTime.now());
    }
}
