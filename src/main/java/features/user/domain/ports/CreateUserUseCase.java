package features.user.domain.ports;

import features.user.domain.models.UserModel;
import features.user.domain.models.records.UserCreateData;

public interface CreateUserUseCase {
    UserModel execute(UserCreateData data);
}
