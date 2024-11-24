package ca.ulaval.glo4003.repUL.application.auth.token;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import ca.ulaval.glo4003.repUL.application.auth.exception.InvalidTokenException;

public interface TokenProvider {
    String generateToken(AuthEntity authEntity);

    AuthEntity validateToken(String token) throws InvalidTokenException;
}

