package ca.ulaval.glo4003.repUL.api.auth.dto;

import jakarta.validation.constraints.NotNull;

public record AuthLoginRequest(
        @NotNull
        String email,
        @NotNull
        String password
) {
}