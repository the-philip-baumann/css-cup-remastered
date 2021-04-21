package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor


public class RegisterDto implements Serializable {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String function;

    private Role role;

    private Discipline discipline;

}
