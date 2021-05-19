package ch.css.lernende.csscupremasteredbackend.model.mapper;

import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {

    public static TeamModel teamDtoToTeamModel(TeamDto teamDto) {
        ch.css.lernende.csscupremasteredbackend.model.TeamModel teamModel = new ch.css.lernende.csscupremasteredbackend.model.TeamModel();
        teamModel.setName(teamDto.getName());
        teamModel.setDiscipline(teamDto.getDiscipline());
        teamModel.setPlayers(Arrays.asList());
        return teamModel;
    }

    public static List<TeamModel> teamDtosToTeamModels(List<TeamDto> teamDtos) {
        return teamDtos.
                stream()
                .map(TeamMapper::teamDtoToTeamModel)
                .collect(Collectors.toList());

    }

    public static TeamDto teamEntityToTeamDto (TeamEntity teamEntity) {
        TeamDto teamDto = new TeamDto();
        teamDto.setName(teamEntity.getName());
        teamDto.setPlayers(Arrays.asList());
        return teamDto;
    }

    public static List<TeamDto> teamEntitiesToTeamDtos(List<TeamEntity> teamEntities) {
        return teamEntities.stream()
                .map(TeamMapper::teamEntityToTeamDto).
                collect(Collectors.toList());
    }

}
