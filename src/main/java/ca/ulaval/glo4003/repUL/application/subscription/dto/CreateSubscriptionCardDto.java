package ca.ulaval.glo4003.repUL.application.subscription.dto;

import java.time.LocalDate;

public record CreateSubscriptionCardDto(
        String CardNumber,
        int ccv,
        LocalDate expDate,
        String fullName
) {}
