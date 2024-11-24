package ca.ulaval.glo4003.repUL.application.auth.factory;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;

public class AuthEntityFactory {
    public AuthEntity toAuthEntity(
            String email,
            String password,
            AuthorizationType authorizationType
    ) {
        return new AuthEntity(email, password, authorizationType);
    }
}
