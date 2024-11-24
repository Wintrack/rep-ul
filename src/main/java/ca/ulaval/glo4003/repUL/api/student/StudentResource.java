package ca.ulaval.glo4003.repUL.api.student;

import ca.ulaval.glo4003.init.servicelocator.ServiceLocator;
import ca.ulaval.glo4003.repUL.api.student.assembler.StudentAssembler;
import ca.ulaval.glo4003.repUL.api.student.dto.StudentRegisterRequest;
import ca.ulaval.glo4003.repUL.application.student.StudentService;
import ca.ulaval.glo4003.repUL.application.student.dto.CreateStudentDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/student")
public class StudentResource {
    private final StudentService studentService;

    private final StudentAssembler studentAssembler;

    public StudentResource() {
        this.studentService = ServiceLocator.getInstance().getService(StudentService.class);
        this.studentAssembler = ServiceLocator.getInstance().getService(StudentAssembler.class);
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@Valid StudentRegisterRequest studentRegisterRequest) {
        CreateStudentDto createdStudent = studentAssembler.toCreateStudentDto(studentRegisterRequest);

        studentService.register(createdStudent);
        return Response.status(Response.Status.CREATED).build();
    }
}
