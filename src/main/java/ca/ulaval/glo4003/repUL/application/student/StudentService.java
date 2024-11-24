package ca.ulaval.glo4003.repUL.application.student;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.application.student.auth.StudentAuth;
import ca.ulaval.glo4003.repUL.application.student.dto.CreateStudentDto;
import ca.ulaval.glo4003.repUL.application.student.factory.CreateStudentFactory;
import ca.ulaval.glo4003.repUL.application.student.infra.StudentRepository;
import ca.ulaval.glo4003.repUL.domain.student.Student;

public class StudentService {
    private final StudentRepository studentRepository;
    
    private final CreateStudentFactory createStudentFactory;

    private final StudentAuth studentAuth;

    public StudentService() {
        this.studentRepository = ServiceLocator.getInstance().getService(StudentRepository.class);
        this.createStudentFactory = ServiceLocator.getInstance().getService(CreateStudentFactory.class);
        this.studentAuth = ServiceLocator.getInstance().getService(StudentAuth.class);
    }

    public void register(CreateStudentDto createStudentDto) {
        Student student = createStudentFactory.toStudent(createStudentDto);

        studentAuth.register(createStudentDto.email(), createStudentDto.password());
        studentRepository.save(student);
    }

    public Student findByEmail(String email) {
        return studentRepository.findByEmailWithoutThrow(email);
    }
}
