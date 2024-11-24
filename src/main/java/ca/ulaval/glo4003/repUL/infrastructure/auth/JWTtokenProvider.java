package ca.ulaval.glo4003.repUL.infrastructure.auth;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;
import ca.ulaval.glo4003.repUL.application.auth.exception.InvalidTokenException;
import ca.ulaval.glo4003.repUL.application.auth.token.TokenProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

public class JWTtokenProvider implements TokenProvider {
    private static final long EXPIRATION_TIME_MILLISECONDS = 60 * 60 * 1000;

    private static final String TYPE_CLAIM = "type";

    Algorithm signatureAlgorithm = Algorithm.HMAC256("secret");

    @Override
    public String generateToken(AuthEntity authorizationData) {
        return JWT.create()
                .withSubject(authorizationData.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MILLISECONDS))
                .withClaim(TYPE_CLAIM, authorizationData.getAuthorizationType().toString())
                .sign(signatureAlgorithm);
    }

    @Override
    public AuthEntity validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(signatureAlgorithm).build();
            DecodedJWT jwt = verifier.verify(token);

            return new AuthEntity(
                    jwt.getSubject(),
                    AuthorizationType.valueOf(jwt.getClaim(TYPE_CLAIM).asString())
            );
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException();
        }
    }

}
