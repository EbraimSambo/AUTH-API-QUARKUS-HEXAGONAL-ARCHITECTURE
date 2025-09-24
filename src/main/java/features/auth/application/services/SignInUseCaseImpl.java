package features.auth.application.services;

import features.auth.domain.ports.SignInUseCase;
import features.auth.infrastructure.jwt.JwtTokenGenerator;
import features.password.domain.models.UserPasswordModel;
import features.password.domain.repository.UserPasswordRepository;
import features.user.domain.models.UserModel;
import features.user.domain.ports.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import shared.domain.exceptions.UnauthorizedException;
import shared.infrastructure.password.PasswordEncoder;

@ApplicationScoped
public class SignInUseCaseImpl implements SignInUseCase {

    @Inject
    UserRepository userRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    UserPasswordRepository passwordRepository;

    @Inject
    JwtTokenGenerator jwtGeneratorToken;

    @Override
    public String execute(String contact, String password) {
        UserModel existingUser = userRepository.findByContact(contact).orElseThrow(
                () -> new UnauthorizedException("Contact notfound"));

        UserPasswordModel userPassword = passwordRepository.findLatestActivePasswordByUserId(existingUser.getId())
                .orElseThrow(() -> new UnauthorizedException("user not has password"));

        System.out.println(userPassword);
        if (!passwordEncoder.verify(userPassword.getValue(), password))
            throw new UnauthorizedException("Password incorrect");

        return jwtGeneratorToken.generator(existingUser, contact);
    }
}
