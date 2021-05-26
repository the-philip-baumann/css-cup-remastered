package ch.css.lernende.csscupremasteredbackend.model.mapper;

import ch.css.lernende.csscupremasteredbackend.dto.CompletePlayerDto;
import ch.css.lernende.csscupremasteredbackend.dto.FullPlayerModel;
import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.dto.PlayerTeamRoleDto;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {
    public static PlayerDto userEntityToUserDto(PlayerEntity playerEntity) {
        return PlayerDto.builder()
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .function(playerEntity.getFunction())
                .build();
    }

    public static List<PlayerDto> listUserEntityToListUserEntity(List<PlayerEntity> playerEntity) {
        return playerEntity.stream().map(PlayerMapper::userEntityToUserDto).collect(Collectors.toList());
    }

    public static FullPlayerModel fullPlayerDtoToPlayerModel(PlayerDto playerDto) {
        return FullPlayerModel.builder()
                .firstname(playerDto.getFirstname())
                .lastname(playerDto.getLastname())
                .email(playerDto.getEmail())
                .function(playerDto.getFunction())
                .role(playerDto.getRole())
                .discipline(playerDto.getDiscipline())
                .build();
    }

    public static CompletePlayerDto playerEntityToCompletePlayerDto(PlayerEntity playerEntity) {
        return CompletePlayerDto.builder()
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .email(playerEntity.getEmail())
                .fucntion(playerEntity.getFirstname())
                .build();
    }

    public static List<PlayerTeamRoleDto> listPlayerEntityToPlayerTeamRoleDto(List<PlayerEntity> playerEntity) {
        return playerEntity.stream().map(PlayerMapper::playerEntityToPlayerTeamRoleDto).collect(Collectors.toList());
    }

    public static PlayerTeamRoleDto playerEntityToPlayerTeamRoleDto(PlayerEntity playerEntity) {
        return PlayerTeamRoleDto.builder()
                .id(playerEntity.getId())
                .firstname(playerEntity.getFirstname())
                .lastname(playerEntity.getLastname())
                .function(playerEntity.getFunction())
                .team(TeamMapper.teamEntityToTeamDto(playerEntity.getPlayerTeam()))
                .role(Role.valueOf(playerEntity.getPlayerRole().getName()))
                .build();
    }



}
