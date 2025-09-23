package features.user.application.services;

import java.util.Optional;
import java.util.UUID;

import features.user.domain.models.UserModel;
import features.user.domain.repository.UserRepository;
import features.user.domain.services.UserService;
import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import shared.domain.exceptions.NotFoundException;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository userRepository;

    @Override
    public Optional<UserModel> findByUUID(UUID uuid) {
        return userRepository.findByUUID(uuid);
    }

    @Override
    public Optional<UserModel> findByContact(String contact) {
        return userRepository.findByContact(contact);
    }

    @Override
    public UserModel validateUserNotFound(UUID uuid) {
        return userRepository.findByUUID(uuid).orElseThrow(
                () -> new NotFoundException("User notFOund"));
    }

    @Override
    public UserModel validateUserUnauthorized(UUID uuid) {
        return userRepository.findByUUID(uuid).orElseThrow(
                () -> new UnauthorizedException("Action Unauthorized"));
    }

}
