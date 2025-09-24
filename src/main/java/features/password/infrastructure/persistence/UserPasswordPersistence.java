package features.password.infrastructure.persistence;

import java.util.Optional;

import features.password.domain.models.UserPasswordModel;
import features.password.domain.repository.UserPasswordRepository;
import features.password.infrastructure.adapters.outbound.mappers.UserPasswordMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserPasswordPersistence implements UserPasswordRepository {

    @Inject
    PanacheUserPasswordPersistenceAdapter adapter;

    @Override
    public Optional<UserPasswordModel> findLatestActivePasswordByUserId(long userId) {
        return adapter.findLatestActivePasswordByUserId(userId).map(UserPasswordMapper::toDomain);
    }

}
