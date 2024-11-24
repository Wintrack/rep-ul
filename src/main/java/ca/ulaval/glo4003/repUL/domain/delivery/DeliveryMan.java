package ca.ulaval.glo4003.repUL.domain.delivery;

import java.time.LocalDate;

public class DeliveryMan {
    private String email;

    private String name;

    private String idul;

    private String location;

    private String sex;

    private LocalDate birthDate;

    public DeliveryMan(
            String email,
            String name, String idul,
            String location,
            String sex,
            LocalDate birthDate
    ) {
        this.email = email;
        this.name = name;
        this.idul = idul;
        this.location = location;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getIdul() {
        return idul;
    }

    public String getLocation() {
        return location;
    }

    public String getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
