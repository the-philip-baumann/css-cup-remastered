package ch.css.lernende.csscupremasteredbackend.model.mapper;

import ch.css.lernende.csscupremasteredbackend.dto.MatchDto;
import ch.css.lernende.csscupremasteredbackend.persistence.MatchEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MatchMapper {

    public static MatchDto matchEntityToMatchDto(MatchEntity matchEntity) {
        return MatchDto.builder()
                .id(matchEntity.getId())
                .field(matchEntity.getField())
                .time(matchEntity.getTime())
                .teams(TeamMapper.teamEntitiesToTeamDtos(matchEntity.getTeams()))
                .build();
    }

    public static List<MatchDto> matchEntitiesToMatchDtos(List<MatchEntity> matchEntities) {
        return matchEntities.stream()
                .map(MatchMapper::matchEntityToMatchDto)
                .collect(Collectors.toList());
    }
}
