package ca.ulaval.glo4003.repUL.api.config.exceptionMappers;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotAuthorizedExceptionMapper implements ExceptionMapper<NotAuthorizedException> {
    @Override
    public Response toResponse(NotAuthorizedException e) {
        ErrorResponse errorResponse = new ErrorResponse("NOT AUTHORIZED", e.getMessage());

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(errorResponse)
                .build();
    }
}
