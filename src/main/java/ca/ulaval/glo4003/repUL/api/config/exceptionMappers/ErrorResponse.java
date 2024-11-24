package ca.ulaval.glo4003.repUL.api.config.exceptionMappers;

public record ErrorResponse(
        String error,
        String message
) {
}

