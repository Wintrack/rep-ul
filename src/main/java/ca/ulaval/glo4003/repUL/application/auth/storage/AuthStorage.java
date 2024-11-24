package ca.ulaval.glo4003.repUL.application.auth.storage;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;

public interface AuthStorage {
    void createAuthEntity(AuthEntity authEntity);

    AuthEntity findByEmail(String email);
}
