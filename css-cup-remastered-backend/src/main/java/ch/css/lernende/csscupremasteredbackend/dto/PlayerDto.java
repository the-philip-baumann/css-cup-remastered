package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class PlayerDto {
    private long id;
    private String firstname;
    private String lastname;
    private String function;
    private String email;
    private Discipline discipline;
    private Role role;
}
