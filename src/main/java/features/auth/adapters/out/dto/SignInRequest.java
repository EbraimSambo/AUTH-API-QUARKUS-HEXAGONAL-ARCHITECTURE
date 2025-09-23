package features.auth.adapters.out.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(
        @NotBlank(message = "contact is required") @Email(message = "contact invalid") @NotBlank(message = "email is required") String contact,
        @NotBlank(message = "password  is required") String password) {

}
