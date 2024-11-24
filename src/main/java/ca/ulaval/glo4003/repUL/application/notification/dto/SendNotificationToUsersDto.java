package ca.ulaval.glo4003.repUL.application.notification.dto;

import java.util.List;

public record SendNotificationToUsersDto(
        List<String> emails,
        String message
) {
}
