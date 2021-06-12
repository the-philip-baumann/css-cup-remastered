package ch.css.lernende.csscupremasteredbackend.dto;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class AddTeamDto {

    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 30)
    private String teamName;

    @NotNull
    private Discipline discipline;

    @NotNull
    @NotEmpty
    private List< PlayerDto> players;

}
