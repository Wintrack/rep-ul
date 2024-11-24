package ca.ulaval.glo4003.repUL.application.auth.entity;

public class AuthEntity {
    private String id;

    private String email;

    private String password;

    private AuthorizationType authorizationType;

    public AuthEntity(
            String email,
            String password,
            AuthorizationType authorizationType
    ) {
        this.id = null;
        this.email = email;
        this.password = password;
        this.authorizationType = authorizationType;
    }

    public AuthEntity(
            String email,
            AuthorizationType authorizationType
    ) {
        this.id = null;
        this.email = email;
        this.password = null;
        this.authorizationType = authorizationType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AuthorizationType getAuthorizationType() {
        return authorizationType;
    }
}
