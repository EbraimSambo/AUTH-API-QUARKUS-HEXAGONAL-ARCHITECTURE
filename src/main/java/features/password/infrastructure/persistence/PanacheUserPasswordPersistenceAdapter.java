package features.password.infrastructure.persistence;

import java.util.Optional;

import features.contact.domain.models.enums.TypeState;
import features.password.infrastructure.adapters.outbound.entities.UserPasswordEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PanacheUserPasswordPersistenceAdapter implements UserPasswordPersistenceAdapter{

    @Override
    public Optional<UserPasswordEntity> findLatestActivePasswordByUserId(long userId) {
       return UserPasswordEntity
                .find("user.id = ?1 and state = ?2 and deletedAt is null",
                        userId, TypeState.ACTIVE)
                .firstResultOptional();
    }

    
}
