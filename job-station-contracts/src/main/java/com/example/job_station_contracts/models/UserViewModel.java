package com.example.job_station_contracts.models;

import java.time.LocalDate;
import java.util.List;

public class UserViewModel {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate joinedAt;
    private LocalDate updatedAt;
    private boolean isBlocked;
    private String blockReason;
    private List<String> contacts;
    private boolean isCommercialAccount;
    private boolean isAdmin;

    public UserViewModel(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String username,
        String password,
        LocalDate joinedAt,
        LocalDate updatedAt,
        boolean isBlocked,
        String blockReason,
        List<String> contacts,
        boolean isCommercialAccount,
        boolean isAdmin
    ) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.joinedAt = joinedAt;
        this.updatedAt = updatedAt;
        this.isBlocked = isBlocked;
        this.blockReason = blockReason;
        this.contacts = contacts;
        this.isCommercialAccount = isCommercialAccount;
        this.isAdmin = isAdmin;
    }

    protected UserViewModel() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDate joinedAt) {
        this.joinedAt = joinedAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public boolean isCommercialAccount() {
        return isCommercialAccount;
    }

    public void setCommercialAccount(boolean isCommercialAccount) {
        this.isCommercialAccount = isCommercialAccount;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
