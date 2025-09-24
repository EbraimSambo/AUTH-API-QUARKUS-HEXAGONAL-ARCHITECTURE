package features.auth.domain.ports;

public interface SignInUseCase {
    String execute(String contact, String password);
}
