package features.user.domain.ports;

import java.util.Optional;
import java.util.UUID;

import features.user.domain.models.UserModel;
import features.user.domain.models.records.UserCreateData;

public interface UserRepository {
    UserModel create(UserCreateData data);
    Optional<UserModel> findByContact(String contact);
    Optional<UserModel> findByUUID(UUID uuid);
}
