package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PlayerTeamRoleDto {
    private long id;
    private String firstname;
    private String lastname;
    private String function;
    private TeamDto team;
    private Role role;
}
