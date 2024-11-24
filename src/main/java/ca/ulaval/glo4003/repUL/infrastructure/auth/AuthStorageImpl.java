package ca.ulaval.glo4003.repUL.infrastructure.auth;

import ca.ulaval.glo4003.repUL.application.auth.entity.AuthEntity;
import ca.ulaval.glo4003.repUL.application.auth.storage.AuthStorage;

import java.util.HashMap;
import java.util.Map;

public class AuthStorageImpl implements AuthStorage {
    Map<String, AuthEntity> authEntityMap;

    public AuthStorageImpl() {
        authEntityMap = new HashMap<>();
    }

    @Override
    public void createAuthEntity(AuthEntity authEntity) {
        authEntityMap.put(authEntity.getEmail(), authEntity);
    }

    @Override
    public AuthEntity findByEmail(String email) {
        return authEntityMap.get(email);
    }
}
