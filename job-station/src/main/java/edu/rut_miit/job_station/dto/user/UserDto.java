package edu.rut_miit.job_station.dto.user;

import java.io.Serializable;
import java.time.LocalDateTime;

public class UserDto implements Serializable {
    private String id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;
    private LocalDateTime joinedAt;
    private LocalDateTime updatedAt;
    private boolean isBlocked;
    private String blockReason;
    private boolean isCommercialAccount;
    private boolean isAdmin;

    public UserDto(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String username,
        String password,
        LocalDateTime joinedAt,
        LocalDateTime updatedAt,
        boolean isBlocked,
        String blockReason,
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
        this.isCommercialAccount = isCommercialAccount;
        this.isAdmin = isAdmin;
    }

    protected UserDto() {}

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public String getBlockReason() {
        return blockReason;
    }

    public boolean isCommercialAccount() {
        return isCommercialAccount;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public void setCommercialAccount(boolean isCommercialAccount) {
        this.isCommercialAccount = isCommercialAccount;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
