package ca.ulaval.glo4003.repUL.application.order.dto;

import java.time.LocalDate;

public record CreateOrderPreparerDto(
        String email,
        String password,
        String idul,
        String name,
        String location,
        String sex,
        LocalDate birthDate
) {}
