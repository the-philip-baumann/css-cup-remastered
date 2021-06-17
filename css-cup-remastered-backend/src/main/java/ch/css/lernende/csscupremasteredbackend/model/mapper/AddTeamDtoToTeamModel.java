package ch.css.lernende.csscupremasteredbackend.model.mapper;

import ch.css.lernende.csscupremasteredbackend.dto.AddTeamDto;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;

import java.util.Arrays;

public class AddTeamDtoToTeamModel {
    public static TeamModel map(AddTeamDto addTeamDto) {
        TeamModel teamModel = new TeamModel();
        teamModel.setName(addTeamDto.getTeamName());
        teamModel.setDiscipline(addTeamDto.getDiscipline());

        //TODO: implement set playsers

        teamModel.setPlayers(Arrays.asList());
        return teamModel;
    }
}
