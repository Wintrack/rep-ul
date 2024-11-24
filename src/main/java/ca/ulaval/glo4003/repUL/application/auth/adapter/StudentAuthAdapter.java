package ca.ulaval.glo4003.repUL.application.auth.adapter;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.auth.AuthService;
import ca.ulaval.glo4003.repUL.application.auth.entity.AuthorizationType;
import ca.ulaval.glo4003.repUL.application.student.auth.StudentAuth;

public class StudentAuthAdapter implements StudentAuth {
    private final AuthService authService;

    public StudentAuthAdapter() {
        this.authService = ServiceLocator.getInstance().getService(AuthService.class);
    }

    @Override
    public void register(String email, String password) {
        authService.register(email, password, AuthorizationType.STUDENT);
    }
}
