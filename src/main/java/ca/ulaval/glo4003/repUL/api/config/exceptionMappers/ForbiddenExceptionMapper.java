package ca.ulaval.glo4003.repUL.api.config.exceptionMappers;

import jakarta.ws.rs.ForbiddenException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException e) {
        ErrorResponse errorResponse = new ErrorResponse("FORBIDDEN", e.getMessage());

        return Response.status(Response.Status.FORBIDDEN)
                .entity(errorResponse)
                .build();
    }
}
