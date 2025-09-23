package features.user.domain.records;

import features.contact.domain.enums.ContactType;
import features.user.domain.enums.Gender;

public record UserCreateData(
    String contact,
    String password,
    Gender gender,
    String name,
    ContactType contactType
) {

}
