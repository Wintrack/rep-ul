package ca.ulaval.glo4003.repUL.domain.student;

import java.time.LocalDate;

public class Student {
    private String email;
    private String idul;
    private String name;
    private String sex;
    private LocalDate birthDate;

    public Student(
            String email,
            String idul,
            String name,
            String sex,
            LocalDate birthDate
    ) {
        this.email = email;
        this.idul = idul;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getIdul() {
        return idul;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}

