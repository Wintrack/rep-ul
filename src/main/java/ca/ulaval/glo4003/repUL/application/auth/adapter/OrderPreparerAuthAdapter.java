package ca.ulaval.glo4003.repUL.application.auth.adapter;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.auth.AuthService;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;
import ca.ulaval.glo4003.repUL.application.order.auth.OrderPreparerAuth;

public class OrderPreparerAuthAdapter implements OrderPreparerAuth {
    private final AuthService authService;

    public OrderPreparerAuthAdapter() {
        this.authService = ServiceLocator.getInstance().getService(AuthService.class);
    }

    @Override
    public void register(String email, String password) {
        this.authService.register(email, password, AuthorizationType.PREPARER);
    }
}
