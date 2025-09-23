import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import features.contact.domain.enums.ContactType;
import features.user.adapters.out.persistence.UserPersistence;
import features.user.domain.enums.Gender;
import features.user.domain.models.UserModel;
import features.user.domain.records.UserCreateData;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@QuarkusTest
public class UserPersistenceTest {
    @Inject
    UserPersistence userPersistence;

    @Test
    @Transactional
    void testCreateUserWithContactAndPassword() {
        UserCreateData data = new UserCreateData(
                "teste@exemplo.com",
                "senha123",
                Gender.MALE,
                "Teste",
                ContactType.EMAIL);
        UserModel result = userPersistence.create(data);
        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
