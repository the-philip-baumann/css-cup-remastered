package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor


public class RegisterDto implements Serializable {

    @NotNull
    @Size(min = 2, max = 30)
    private String firstname;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastname;

    @NotNull
    @Size(min = 5, max = 30)
    @Email
    private String email;

    @NotNull
    @Size(min = 5, max = 30)
    private String password;

    @NotNull
    @Size(min = 5, max = 30)
    private String function;

    @NotNull
    private String role;

    @NotNull
    private String discipline;

}
