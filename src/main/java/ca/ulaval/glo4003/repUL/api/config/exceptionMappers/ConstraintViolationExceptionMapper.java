package ca.ulaval.glo4003.repUL.api.config.exceptionMappers;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_BODY", e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}


