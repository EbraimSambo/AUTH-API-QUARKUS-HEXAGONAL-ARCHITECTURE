package features.user.domain.models.records;

import features.contact.domain.models.enums.ContactType;
import features.user.domain.models.enums.Gender;

public record UserCreateData(
    String contact,
    String password,
    Gender gender,
    String name,
    ContactType contactType
) {

}
