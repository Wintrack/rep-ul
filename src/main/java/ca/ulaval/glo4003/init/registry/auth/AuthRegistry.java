package ca.ulaval.glo4003.init.registry.auth;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.auth.adapter.DeliveryManAuthAdapter;
import ca.ulaval.glo4003.repUL.application.auth.adapter.OrderPreparerAuthAdapter;
import ca.ulaval.glo4003.repUL.application.auth.adapter.StudentAuthAdapter;
import ca.ulaval.glo4003.repUL.application.auth.storage.AuthStorage;
import ca.ulaval.glo4003.repUL.application.auth.token.TokenProvider;
import ca.ulaval.glo4003.repUL.application.delivery.auth.DeliveryManAuth;
import ca.ulaval.glo4003.repUL.application.order.auth.OrderPreparerAuth;
import ca.ulaval.glo4003.repUL.application.student.auth.StudentAuth;
import ca.ulaval.glo4003.repUL.infrastructure.auth.AuthStorageImpl;
import ca.ulaval.glo4003.repUL.infrastructure.auth.JWTtokenProvider;

public class AuthRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(TokenProvider.class, new JWTtokenProvider());
        serviceLocator.registerService(AuthStorage.class, new AuthStorageImpl());
        serviceLocator.registerService(OrderPreparerAuth.class, new OrderPreparerAuthAdapter());
        serviceLocator.registerService(StudentAuth.class, new StudentAuthAdapter());
        serviceLocator.registerService(DeliveryManAuth.class, new DeliveryManAuthAdapter());
    }
}
