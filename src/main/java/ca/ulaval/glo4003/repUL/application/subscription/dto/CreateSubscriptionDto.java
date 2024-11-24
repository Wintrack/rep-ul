package ca.ulaval.glo4003.repUL.application.subscription.dto;

import java.time.LocalDateTime;

public record CreateSubscriptionDto(
        String userEmail,
        CreateSubscriptionCardDto creditCard,
        String foodBoxId,
        LocalDateTime frequency,
        String location
) {}
