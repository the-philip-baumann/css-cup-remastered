package ch.css.lernende.csscupremasteredbackend.model;

public enum Role {
    ROLE_ADMIN ("ROLE_ADMIN"),
    ROLE_PARTICIPANT ("ROLE_PARTICIPANT"),
    ROLE_CAPTAIN ("ROLE_CAPTAIN"),
    ROLE_UNDECIDED ("ROLE_NOT_REGISTERED");

    public final String role;

    Role(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return this.role;
    }

}
