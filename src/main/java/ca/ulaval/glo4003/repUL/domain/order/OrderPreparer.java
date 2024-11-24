package ca.ulaval.glo4003.repUL.domain.order;

import java.util.Objects;

public class OrderPreparer {
    private String email;

    private final String name;

    private final String location;

    public OrderPreparer(String email, String name, String location) {
        this.email = email;
        this.name = name;
        this.location = location;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPreparer that = (OrderPreparer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
