package ca.ulaval.glo4003.repUL.api.student.assembler;

import ca.ulaval.glo4003.repUL.api.student.dto.StudentRegisterRequest;
import ca.ulaval.glo4003.repUL.application.student.dto.CreateStudentDto;

public class StudentAssembler {
    public CreateStudentDto toCreateStudentDto(StudentRegisterRequest studentRegisterRequest) {
        return new CreateStudentDto(
                studentRegisterRequest.idul(),
                studentRegisterRequest.name(),
                studentRegisterRequest.sex(),
                studentRegisterRequest.birthDate(),
                studentRegisterRequest.email(),
                studentRegisterRequest.password()
        );
    }
}
