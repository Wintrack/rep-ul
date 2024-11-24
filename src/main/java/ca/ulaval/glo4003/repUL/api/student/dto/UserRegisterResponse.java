package ca.ulaval.glo4003.repUL.api.student.dto;

import java.time.LocalDate;

public record UserRegisterResponse(
        String userId,
        String idul,
        String name,
        String sex,
        LocalDate birthDate,
        String email) {
}