package ca.ulaval.glo4003.repUL.application.subscription.dto;

public record CreateSporadicSubscriptionDto(
        String userEmail,
        CreateSubscriptionCardDto creditCard,
        String foodBoxId,
        String location
) {}
