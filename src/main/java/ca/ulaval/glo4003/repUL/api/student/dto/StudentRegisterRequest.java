package ca.ulaval.glo4003.repUL.api.student.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StudentRegisterRequest(
        @NotNull(message = "Idul should not be empty")
        String idul,
        @NotNull(message = "User ingredient should not be empty")
        String name,
        @NotNull(message = "User sex should not be empty")
        String sex,
        @NotNull(message = "User birthdate should not be empty")
        LocalDate birthDate,
        @NotNull
        String email,
        @NotNull
        String password
) {
}
