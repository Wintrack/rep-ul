package ca.ulaval.glo4003.repUL.infrastructure.user;

import ca.ulaval.glo4003.repUL.application.student.infra.StudentRepository;
import ca.ulaval.glo4003.repUL.domain.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryStudentRepositoryImplTest {
    private static final String USER_ID = UUID.randomUUID().toString();
    private static final String IDUL = "TEST12";
    private static final String NAME = "BOB TheTestor";
    private static final String SEX = "male";
    private static final LocalDate BIRTH_DATE = LocalDate.now();

    private StudentRepository studentRepository;

    @BeforeEach
    void setup() {
        studentRepository = new InMemoryStudentRepositoryImpl();
    }

    @Test
    void givenUserCreated_whenFindByEmailWithoutThrow_ThenReturnUserFound() {
        // GIVEN
        Student student = new Student(USER_ID, IDUL, NAME, SEX, BIRTH_DATE);

        studentRepository.save(student);

        // WHEN
        Student studentFound = studentRepository.findByEmailWithoutThrow(student.getEmail());

        // THEN
        assertThat(studentFound).isEqualTo(student);
    }

    @Test
    void givenUserCreated_whenFindByIdulWithoutThrow_ThenReturnUserFound() {
        // GIVEN
        Student student = new Student(USER_ID, IDUL, NAME, SEX, BIRTH_DATE);

        studentRepository.save(student);

        // WHEN
        Student studentFound = studentRepository.findByIdulWithoutThrow(student.getIdul());

        // THEN
        assertThat(studentFound).isEqualTo(student);
    }

}