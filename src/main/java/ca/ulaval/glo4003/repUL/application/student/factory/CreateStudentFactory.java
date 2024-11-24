package ca.ulaval.glo4003.repUL.application.student.factory;

import ca.ulaval.glo4003.repUL.application.student.dto.CreateStudentDto;
import ca.ulaval.glo4003.repUL.domain.student.Student;
import jakarta.validation.Valid;

public class CreateStudentFactory {
    public Student toStudent(@Valid CreateStudentDto createStudentDto) {
        return new Student(
                createStudentDto.email(),
                createStudentDto.idul(),
                createStudentDto.name(),
                createStudentDto.sex(),
                createStudentDto.birthDate()
        );
    }
}
