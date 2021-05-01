package ch.css.lernende.csscupremasteredbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerModel {
    private String firstname;
    private String lastname;
    private Discipline discipline;
    private Role role;
}
