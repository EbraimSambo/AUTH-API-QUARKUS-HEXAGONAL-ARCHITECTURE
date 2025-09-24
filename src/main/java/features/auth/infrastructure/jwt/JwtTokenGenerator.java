package features.auth.infrastructure.jwt;

import org.eclipse.microprofile.jwt.Claims;

import features.user.domain.models.UserModel;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtTokenGenerator {

    public String generator(UserModel user, String contact) {
        return Jwt.issuer("auth-service")
                .subject(user.getUuid().toString())
                .claim(Claims.email.name(), contact)
                .groups("user")
                .expiresIn(864_000_000)
                .sign();
    }
}
