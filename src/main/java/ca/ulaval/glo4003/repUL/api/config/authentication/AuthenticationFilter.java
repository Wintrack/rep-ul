package ca.ulaval.glo4003.repUL.api.config.authentication;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.config.authentication.annotation.Protected;
import ca.ulaval.glo4003.repUL.api.config.authentication.exception.InvalidCredentialFilterException;
import ca.ulaval.glo4003.repUL.application.auth.AuthService;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    private final AuthService authService;

    public AuthenticationFilter() {
        this.authService = ServiceLocator.getInstance().getService(AuthService.class);
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        Protected aProtected = checkIfSecuredRoute();

        if (aProtected == null) {
            return;
        }

        String authenticationToken = getAuthenticationToken(containerRequestContext);
        AuthEntity authEntity = authService.validateToken(
                authenticationToken,
                aProtected.value()
        );

        containerRequestContext
                .setSecurityContext(
                        new JwtSecurityContext(
                                new AuthenticatedUserPrincipal(authEntity.getEmail())
                        )
                );
    }

    private Protected checkIfSecuredRoute() {
        if (resourceInfo.getResourceMethod().isAnnotationPresent(Protected.class)) {
            return resourceInfo.getResourceMethod().getAnnotation(Protected.class);
        }
        if (resourceInfo.getResourceClass().isAnnotationPresent(Protected.class)) {
            return resourceInfo.getResourceClass().getAnnotation(Protected.class);
        }
        return null;
    }

    private String getAuthenticationToken(ContainerRequestContext containerRequestContext) {
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null) {
            throw new InvalidCredentialFilterException();
        }
        if (!authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidCredentialFilterException();
        }
        return authorizationHeader.substring(7);
    }

    public void setResourceInfo(ResourceInfo resourceInfo) {
        this.resourceInfo = resourceInfo;
    }
}
