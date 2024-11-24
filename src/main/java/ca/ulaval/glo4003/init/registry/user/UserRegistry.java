package ca.ulaval.glo4003.init.registry.user;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.student.infra.StudentRepository;
import ca.ulaval.glo4003.repUL.infrastructure.user.InMemoryStudentRepositoryImpl;

public class UserRegistry {
    public void register() {
        ServiceLocator serviceLocator = ServiceLocator.getInstance();

        serviceLocator.registerService(StudentRepository.class, new InMemoryStudentRepositoryImpl());
    }
}
