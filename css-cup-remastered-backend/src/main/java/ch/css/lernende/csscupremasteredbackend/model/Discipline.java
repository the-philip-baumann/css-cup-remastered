package ch.css.lernende.csscupremasteredbackend.model;

public enum Discipline {
    FOOTBALL ("football"),
    VOLLEYBALL ("volleyball"),
    UNDECIDED ("open");

    private final String value;

    Discipline (String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
