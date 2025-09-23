package features.password.adapters.out.persistence;

import java.util.Optional;

import features.contact.domain.enums.TypeState;
import features.password.adapters.out.entities.UserPasswordEntity;
import features.password.domain.models.UserPasswordModel;
import features.password.domain.repository.UserPasswordRepository;
import jakarta.enterprise.context.ApplicationScoped;
import features.password.adapters.out.mappers.UserPasswordMapper;

@ApplicationScoped
public class UserPasswordPersistence implements UserPasswordRepository {

    @Override
    public Optional<UserPasswordModel> findLatestActivePasswordByUserId(long userId) {
        Optional<UserPasswordEntity> entity = UserPasswordEntity.find("user.id = ?1 and state = ?2 and deletedAt is null", 
                    userId, TypeState.ACTIVE)
                    .firstResultOptional();
        return entity.map(UserPasswordMapper::toDomain);
    }

    
}
