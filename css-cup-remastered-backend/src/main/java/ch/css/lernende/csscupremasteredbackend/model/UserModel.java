package ch.css.lernende.csscupremasteredbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserModel {

    private String firstname;
    private String lastname;
    private String function;
    private String email;
    private String password;
    private Role role;
    private Discipline discipline;

}
