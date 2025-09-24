package features.auth.infrastructure.adapters.outbound.dto;

import features.contact.domain.models.enums.ContactType;
import features.user.domain.models.enums.Gender;
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
