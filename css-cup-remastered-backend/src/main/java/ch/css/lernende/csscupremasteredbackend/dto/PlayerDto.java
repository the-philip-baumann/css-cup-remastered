package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class PlayerDto {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String firstname;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastname;

    @NotNull
    @Size(min = 3, max = 20)
    private String function;

    @NotNull
    @Email
    private String email;

    private Discipline discipline;

    @NotNull
    private Role role;
}
