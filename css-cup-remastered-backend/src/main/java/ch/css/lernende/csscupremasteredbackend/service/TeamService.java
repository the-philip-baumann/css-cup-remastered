package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.AddTeamDto;
import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import ch.css.lernende.csscupremasteredbackend.model.mapper.TeamMapper;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    public List<TeamDto> fetchAll() {
        Iterable<TeamEntity> teams = teamRepository.findAll();
        List<TeamEntity> list = StreamSupport.stream(teams.spliterator(), true)
                .collect(Collectors.toList());
        return TeamMapper.teamEntitiesToTeamDtos(list);
    }

    public TeamDto fetchTeam(Optional<Long> id) throws NoResultsFoundException, IllegalParameterException {

        if (id.isEmpty()) {
            throw new IllegalParameterException();
        }

        Optional<TeamEntity> teamEntity = teamRepository.findById(id.get());

        if (teamEntity.isEmpty()) {
            throw new NoResultsFoundException();
        }

        return TeamMapper.teamEntityToTeamDto(teamRepository.findById(id.get()).get());

    }

    public void addTeam(AddTeamDto addTeamDto) throws IllegalParameterException {
        //TODO: Remove teamp captain and implement real user
        Optional<PlayerEntity> playerEntity = this.playerRepository.findById(addTeamDto.getUserId());
        playerEntity.orElseThrow(IllegalParameterException::new);

        teamRepository.insertTeam(addTeamDto.getName(), addTeamDto.getDiscipline(), playerEntity.get());
        TeamEntity teamEntity = teamRepository.findByName(addTeamDto.getName());

        addTeamDto.getPlayers()
                .stream()
                .parallel()
                .forEach(player -> playerRepository.addTeamToPlayer(teamEntity, player.getId()));
    }

    public void deleteTeam(Optional<Long> id) throws IllegalArgumentException {
        if(id.isEmpty()) {
            throw new IllegalArgumentException();
        }

        teamRepository.deleteById(id.get());
    }

    public void renameTeam(long id, String name) {
        // TODO: Implement
    }

    public void joinTeam(long userId, Optional<Long> id) throws IllegalParameterException {

        long teamId = id.orElseThrow(IllegalParameterException::new);

        this.teamRepository.addPlayerToTeam(userId, teamId);

    }
}
