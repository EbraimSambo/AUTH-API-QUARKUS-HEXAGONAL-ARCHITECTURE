package features.auth.adapters.out.dto;

import features.contact.domain.enums.ContactType;
import features.user.domain.enums.Gender;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDto(
        @NotBlank(message = "contact is required") String contact,
        @NotBlank(message = "name  is required") String name,
        @NotBlank(message = "password is required") String password,
        @NotNull Gender gender,
        @NotNull ContactType contactType
        ) {

}
