package features.contact.adapters.out.entities;

import java.time.LocalDateTime;

import features.contact.domain.enums.ContactType;
import features.contact.domain.enums.TypeState;
import features.user.adapters.out.entities.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class UserContactEntity extends PanacheEntity {
    @Column(nullable = false, unique = true)
    private String value;
    @Enumerated(EnumType.STRING)
    private TypeState state;
    @Enumerated(EnumType.STRING)
    private ContactType type;
    @ManyToOne // Alterado de OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(nullable = true, name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.getState() == null) {
            this.setState(TypeState.ACTIVE);
        }
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public TypeState getState() {
        return state;
    }

    public void setState(TypeState state) {
        this.state = state;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

}
