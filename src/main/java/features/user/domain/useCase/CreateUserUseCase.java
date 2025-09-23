package features.user.domain.useCase;

import features.user.domain.models.UserModel;
import features.user.domain.records.UserCreateData;

public interface CreateUserUseCase {
    UserModel execute(UserCreateData data);
}
