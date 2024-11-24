package ca.ulaval.glo4003.repUL.api.config.exceptionMappers;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse("RESOURCE NOT FOUND", e.getMessage());

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}
