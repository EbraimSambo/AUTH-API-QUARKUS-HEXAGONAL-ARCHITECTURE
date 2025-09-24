package features.user.application.useCase;

import java.util.Optional;

import org.jboss.logging.Logger;

import features.user.domain.models.UserModel;
import features.user.domain.models.records.UserCreateData;
import features.user.domain.ports.CreateUserUseCase;
import features.user.domain.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import shared.domain.exceptions.ConflictException;

@ApplicationScoped
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private static final Logger LOG = Logger.getLogger(CreateUserUseCaseImpl.class);

    @Inject
    UserRepository userRepository;

    @Override
    public UserModel execute(UserCreateData data) {
        Optional<UserModel> existingUser = userRepository.findByContact(data.contact());
        if (existingUser.isPresent()) {
            LOG.warnf("Email já existe: %s", data.contact());
            throw new ConflictException("contact has existing");
        }
        return userRepository.create(data);
    }

}
