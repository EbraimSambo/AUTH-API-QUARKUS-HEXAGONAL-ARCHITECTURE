package shared.infrastructure.password;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class PasswordEncoder {
    private final Argon2 argon2 = Argon2Factory.create();

    public String hash(String password) {
        return argon2.hash(3, 65536, 1, password);
    }

    public boolean verify(String hash, String password) {
        return argon2.verify(hash, password);
    }
}
