package ca.ulaval.glo4003.repUL.api.config.authentication;

import java.security.Principal;
import java.util.Objects;

public class AuthenticatedUserPrincipal implements Principal {
    private final String email;

    public AuthenticatedUserPrincipal(String email) {
        this.email = email;
    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticatedUserPrincipal that = (AuthenticatedUserPrincipal) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
