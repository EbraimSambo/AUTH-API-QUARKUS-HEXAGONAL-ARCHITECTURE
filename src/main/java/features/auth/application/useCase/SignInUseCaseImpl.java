package features.auth.application.useCase;

import org.eclipse.microprofile.jwt.Claims;

import features.auth.domain.useCase.SignInUseCase;
import features.password.domain.models.UserPasswordModel;
import features.password.domain.repository.UserPasswordRepository;
import features.user.domain.models.UserModel;
import features.user.domain.repository.UserRepository;
import io.smallrye.jwt.build.Jwt;
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

    @Override
    public String execute(String contact, String password) {
        UserModel existingUser = userRepository.findByContact(contact).orElseThrow(
                () -> new UnauthorizedException("Contact notfound"));

        UserPasswordModel userPassword = passwordRepository.findLatestActivePasswordByUserId(existingUser.getId())
                .orElseThrow(() -> new UnauthorizedException("user not has password"));

        System.out.println(userPassword);
        if (!passwordEncoder.verify(userPassword.getValue(), password))
            throw new UnauthorizedException("Password incorrect");

        return Jwt.issuer("auth-service")
                .subject(existingUser.getUuid().toString())
                .claim(Claims.email.name(), contact)
                .groups("user")
                .expiresIn(864_000_000)
                .sign();
    }
}
