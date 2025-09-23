package features.user.domain.services;

import java.util.Optional;
import java.util.UUID;

import features.user.domain.models.UserModel;

public interface UserService {
    Optional<UserModel> findByContact(String contact);
    Optional<UserModel> findByUUID(UUID uuid);
    UserModel validateUserNotFound(UUID uuid);
    UserModel validateUserUnauthorized(UUID uuid);
}
