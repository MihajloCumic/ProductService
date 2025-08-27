package com.example.product_service.dto.user.auth;

import com.example.product_service.dto.user.UserDetails;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistrationDto(
        @JsonUnwrapped @Valid UserDetails details,
        @NotBlank @Size(min = 8, max = 128) String password,
        @NotBlank @Size(min = 8, max = 128) String passwordRepeat

) {
    @AssertTrue(message = "Passwords do not match.")
    public boolean doPasswordsMatch(){
        return password.equals(passwordRepeat);
    }
}
