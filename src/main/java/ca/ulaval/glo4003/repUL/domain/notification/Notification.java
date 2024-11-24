package ca.ulaval.glo4003.repUL.domain.notification;

public class Notification {

    private String id;

    private String email;

    private String message;

    public Notification(String email, String message) {
        this.id = null;
        this.email = email;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getEmail() { return email; }

    public String getMessage() {
        return message;
    }

    public void setId(String id) {
        this.id = id;
    }
}
