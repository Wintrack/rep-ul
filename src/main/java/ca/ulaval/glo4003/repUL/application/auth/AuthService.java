package ca.ulaval.glo4003.repUL.application.auth;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.auth.exception.AuthEntityAlreadyExists;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;
import ca.ulaval.glo4003.repUL.application.auth.exception.BadCredentialException;
import ca.ulaval.glo4003.repUL.application.auth.exception.UserNotAuthorizedException;
import ca.ulaval.glo4003.repUL.application.auth.factory.AuthEntityFactory;
import ca.ulaval.glo4003.repUL.application.auth.storage.AuthStorage;
import ca.ulaval.glo4003.repUL.application.auth.token.TokenProvider;

import static ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType.ALL;

public class AuthService  {
    private final AuthStorage authStorage;

    private final AuthEntityFactory authEntityFactory;
    
    private final TokenProvider tokenProvider;

    public AuthService() {
        this.authStorage = ServiceLocator.getInstance().getService(AuthStorage.class);
        this.authEntityFactory = ServiceLocator.getInstance().getService(AuthEntityFactory.class);
        this.tokenProvider = ServiceLocator.getInstance().getService(TokenProvider.class);
    }

    public void register(
            String email,
            String password,
            AuthorizationType authorizationType
    ) {
        AuthEntity authEntity = authEntityFactory.toAuthEntity(email, password, authorizationType);
        AuthEntity authEntityFound = authStorage.findByEmail(email);

        if (authEntityFound != null) {
            throw new AuthEntityAlreadyExists();
        }
        authStorage.createAuthEntity(authEntity);
    }

    public String authenticate(String email, String password) {
        AuthEntity auth = authStorage.findByEmail(email);

        if (auth == null || !auth.getPassword().equals(password)) {
            throw new BadCredentialException();
        }
        return tokenProvider.generateToken(
                new AuthEntity(
                        auth.getEmail(),
                        auth.getAuthorizationType()
                )
        );
    }

    public AuthEntity validateToken(String token, AuthorizationType authorizationType) {
        AuthEntity authEntity = tokenProvider.validateToken(token);

        if (authorizationType == ALL || authorizationType == authEntity.getAuthorizationType()) {
            return authEntity;
        }
        throw new UserNotAuthorizedException();
    }
}
