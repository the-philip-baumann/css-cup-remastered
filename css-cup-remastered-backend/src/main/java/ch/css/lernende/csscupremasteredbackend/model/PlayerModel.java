package ch.css.lernende.csscupremasteredbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PlayerModel {
    private String firstname;
    private String lastname;
    private Discipline discipline;
    private Role role;
}
