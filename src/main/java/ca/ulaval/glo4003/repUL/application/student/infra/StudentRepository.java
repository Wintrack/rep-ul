package ca.ulaval.glo4003.repUL.application.student.infra;

import ca.ulaval.glo4003.repUL.domain.student.Student;

public interface StudentRepository {
    void save(Student student);

    Student findByEmailWithoutThrow(String email);

    Student findByIdulWithoutThrow(String idul);
}
