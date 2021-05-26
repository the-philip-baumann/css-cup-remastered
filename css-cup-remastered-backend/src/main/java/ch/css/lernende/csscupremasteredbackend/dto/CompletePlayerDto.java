package ch.css.lernende.csscupremasteredbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CompletePlayerDto {
    private String firstname;
    private String lastname;
    private String fucntion;
    private String email;
    private TeamDto team;
    private DisciplineDto discipline;
}
