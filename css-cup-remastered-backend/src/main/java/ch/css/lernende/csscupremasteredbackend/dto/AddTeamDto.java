package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddTeamDto {
    private long userId;
    private String name;
    private Discipline discipline;
    private List<PlayerDto> players;
}
