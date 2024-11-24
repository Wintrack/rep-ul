package ca.ulaval.glo4003.repUL.domain.order.exception;

import jakarta.ws.rs.BadRequestException;

public class selectionImpossibleException extends BadRequestException {
    private static final String MESSAGE = "Impossible to select or deselect order," +
            " order not in preparation";

    public selectionImpossibleException() {
        super(MESSAGE);
    }
}
