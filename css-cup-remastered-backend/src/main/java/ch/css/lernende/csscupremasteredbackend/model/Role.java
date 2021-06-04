package ch.css.lernende.csscupremasteredbackend.model;

public enum Role {
    ADMIN ("ADMIN"),
    PARTICIPANT ("PARTICIPANT"),
    CAPTAIN ("CAPTAIN"),
    NOT_REGISTERED ("NOT_REGISTERED");

    public final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }

}
