package ca.ulaval.glo4003.repUL.infrastructure.user;

import ca.ulaval.glo4003.repUL.application.student.infra.StudentRepository;
import ca.ulaval.glo4003.repUL.domain.student.Student;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStudentRepositoryImpl implements StudentRepository {
    private final Map<String, Student> users = new HashMap<>();

    @Override
    public void save(Student student) {
        users.put(student.getEmail(), student);
    }

    @Override
    public Student findByEmailWithoutThrow(String email) {
        return users.get(email);
    }

    @Override
    public Student findByIdulWithoutThrow(String idul) {
        return users.values()
                .stream()
                .filter(user -> user.getIdul().equals(idul))
                .findFirst()
                .orElse(null);
    }
}
