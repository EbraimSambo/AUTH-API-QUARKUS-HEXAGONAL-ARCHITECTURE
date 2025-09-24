package features.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import features.user.infrastructure.adapters.outbound.entities.UserEntity;

public interface UserPersistenceAdapter {
    void save(UserEntity entity);

    Optional<UserEntity> findByContact(String contact);

    Optional<UserEntity> findByUUID(UUID uuid);
}
