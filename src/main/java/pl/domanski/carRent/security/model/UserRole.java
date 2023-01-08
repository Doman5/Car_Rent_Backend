package pl.domanski.carRent.security.model;

public enum UserRole {
    ROLE_ADMIN("ADMIN"),
    ROLE_CUSTOMER("CUSTOMER"),
    ROLE_WORKER("WORKER");

    private final String role;

    UserRole(String value) {
        this.role = value;
    }

    public String getRole() {
        return role;
    }
}
