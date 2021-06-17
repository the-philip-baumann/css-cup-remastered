package ch.css.lernende.csscupremasteredbackend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class LoginDto {
    @NotNull
    @Size(min = 4, max = 50)
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 30)
    private String password;
}
