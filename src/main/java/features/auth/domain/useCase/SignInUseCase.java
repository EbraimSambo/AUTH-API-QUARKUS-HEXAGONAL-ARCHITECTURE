package features.auth.domain.useCase;

public interface SignInUseCase {
    String execute(String contact, String password);
}
