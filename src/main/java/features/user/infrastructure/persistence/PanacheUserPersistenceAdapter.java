package features.user.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import features.contact.domain.models.enums.TypeState;
import features.contact.infrastructure.adapters.outbound.entities.UserContactEntity;
import features.user.infrastructure.adapters.outbound.entities.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheUserPersistenceAdapter implements UserPersistenceAdapter {

    @Override
    public Optional<UserEntity> findByContact(String contact) {
        return UserContactEntity
                .find("value = ?1 and state = ?2", contact, TypeState.ACTIVE)
                .firstResultOptional();
    }

    @Override
    public void save(UserEntity entity) {
        entity.persist();
    }

    @Override
    public Optional<UserEntity> findByUUID(UUID uuid) {
        return UserEntity.find("uuid", uuid).firstResultOptional();
    }

}
