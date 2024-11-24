package ca.ulaval.glo4003.repUL.api.config.authentication;

import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.Objects;

public class JwtSecurityContext implements SecurityContext {
    private final AuthenticatedUserPrincipal authenticatedUserPrincipal;

    public JwtSecurityContext(
            AuthenticatedUserPrincipal authenticatedUserPrincipal
    ) {
        this.authenticatedUserPrincipal = authenticatedUserPrincipal;
    }


    @Override
    public Principal getUserPrincipal() {
        return authenticatedUserPrincipal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return "JWT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtSecurityContext that = (JwtSecurityContext) o;
        return Objects.equals(authenticatedUserPrincipal, that.authenticatedUserPrincipal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authenticatedUserPrincipal);
    }
}
