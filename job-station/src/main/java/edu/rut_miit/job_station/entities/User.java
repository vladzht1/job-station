package edu.rut_miit.job_station.entities;

import edu.rut_miit.job_station.exceptions.ClientException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
    private boolean isBlocked;
    private String blockReason;
    private Set<Resume> resumes;
    private boolean isCommercialAccount;
    private List<Role> roles;

    public User(String firstName, String middleName, String lastName, String username, String password, boolean isCommercialAccount) {
        this();

        setFirstName(firstName);
        setMiddleName(middleName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setBlocked(false);
        setBlockReason(null);
        setResumes(new HashSet<>());
        setCommercialAccount(isCommercialAccount);

        setJoinedAt(LocalDateTime.now());
        setUpdatedAt(LocalDateTime.now());
    }

    protected User() {
        this.roles = new ArrayList<>();
    }

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

    @Column(name = "username")
    public String getUsername() {
        return username;
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

    @Column(name = "is_commercial_account")
    public boolean isCommercialAccount() {
        return isCommercialAccount;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> getRoles() {
        return roles;
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
            throw new ClientException.InvalidStateException("User is not blocked");
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

    public void setUsername(String username) {
        this.username = username;
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

    public void setCommercialAccount(boolean isCommercialAccount) {
        this.isCommercialAccount = isCommercialAccount;
        markAtUpdated();
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
            throw new ClientException.InvalidStateException("User is already blocked");
        }

        setBlocked(true);
    }

    private void markAtUpdated() {
        setUpdatedAt(LocalDateTime.now());
    }
}
