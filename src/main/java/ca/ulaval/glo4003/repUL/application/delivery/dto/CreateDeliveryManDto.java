package ca.ulaval.glo4003.repUL.application.delivery.dto;

import java.time.LocalDate;

public record CreateDeliveryManDto(
        String email,
        String password,
        String idul,
        String name,
        String location,
        String sex,
        LocalDate birthDate
) {}
