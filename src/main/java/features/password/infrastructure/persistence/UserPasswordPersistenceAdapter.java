package features.password.infrastructure.persistence;

import java.util.Optional;
import features.password.infrastructure.adapters.outbound.entities.UserPasswordEntity;

public interface UserPasswordPersistenceAdapter {
    Optional<UserPasswordEntity> findLatestActivePasswordByUserId(long userId);
}
