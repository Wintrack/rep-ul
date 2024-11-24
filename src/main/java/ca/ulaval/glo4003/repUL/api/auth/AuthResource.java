package ca.ulaval.glo4003.repUL.api.auth;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.auth.dto.AuthLoginRequest;
import ca.ulaval.glo4003.repUL.api.student.dto.TokenResponse;
import ca.ulaval.glo4003.repUL.application.auth.AuthService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("auth")
public class AuthResource {
    private final AuthService authService;

    public AuthResource() {
        this.authService = ServiceLocator.getInstance().getService(AuthService.class);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(@Valid AuthLoginRequest authLoginRequest) {
        String token = authService.authenticate(authLoginRequest.email(), authLoginRequest.password());
        TokenResponse tokenResponse = new TokenResponse(token);

        return Response.status(Response.Status.OK).entity(tokenResponse).build();
    }
}
