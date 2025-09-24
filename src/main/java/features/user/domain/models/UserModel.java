package features.user.domain.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import features.contact.domain.models.UserContactModel;
import features.password.domain.models.UserPasswordModel;
import features.user.domain.models.enums.Gender;

public class UserModel {
    private long id;
    private UUID uuid;
    private Gender gender = Gender.MALE;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private List<UserContactModel> contacts = new ArrayList<>();
    private List<UserPasswordModel> passwords  = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserPasswordModel> getPasswords() {
        return Collections.unmodifiableList(passwords);
    }

    public void setPasswords(List<UserPasswordModel> passwords) {
        this.passwords = passwords;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<UserContactModel> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    public void setContacts(List<UserContactModel> contacts) {
        this.contacts = contacts;
    }
}
