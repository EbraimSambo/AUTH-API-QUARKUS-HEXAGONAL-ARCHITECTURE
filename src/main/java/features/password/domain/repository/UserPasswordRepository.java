package features.password.domain.repository;

import java.util.Optional;

import features.password.domain.models.UserPasswordModel;

public interface UserPasswordRepository {
    Optional<UserPasswordModel> findLatestActivePasswordByUserId(long userId);
}
