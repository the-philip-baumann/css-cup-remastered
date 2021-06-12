package ch.css.lernende.csscupremasteredbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthStateDto {
    private String jwt;
    private String error;
}
