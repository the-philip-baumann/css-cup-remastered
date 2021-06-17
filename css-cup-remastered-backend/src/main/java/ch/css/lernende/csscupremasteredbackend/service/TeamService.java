package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.AddTeamDto;
import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.model.mapper.TeamMapper;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.discipline.DisciplineRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.team.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    private final DisciplineRepository disciplineRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository, PlayerRepository playerRepository, DisciplineRepository disciplineRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
        this.disciplineRepository = disciplineRepository;
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

    public void addTeam(AddTeamDto addTeamDto) throws IllegalParameterException, SQLException {

        Optional<DisciplineEntity> disciplineEntity = disciplineRepository.findByName(addTeamDto.getDiscipline().name());

        if (disciplineEntity.isEmpty()) {
            throw new IllegalParameterException();
        }

        teamRepository.insertTeam(addTeamDto.getTeamName(), disciplineEntity.get());
        TeamEntity teamEntity = teamRepository.findByName(addTeamDto.getTeamName());

        for (long id : addTeamDto.getPlayers()) {
            Optional<PlayerEntity> playerEntity = playerRepository.findById(id);
            playerRepository.addTeamToPlayer(teamEntity, playerEntity.orElseThrow(IllegalParameterException::new).getId());
        }
    }

    public void deleteTeam(Optional<Long> id) throws IllegalParameterException {
        if(id.isEmpty()) {
            throw new IllegalParameterException();
        }

        Optional<TeamEntity> teamEntity = teamRepository.findById(id.get());

        if (teamEntity.isEmpty()) {
            throw new IllegalParameterException();
        }

        teamEntity.get().getPlayers().stream().parallel().forEach(playerEntity -> {
            playerEntity.setPlayerTeam(null);
            playerRepository.save(playerEntity);
        });

        teamRepository.deleteById(id.get());
    }

    public void joinTeam(Optional<Long> teamId, Optional<Long> userId) throws IllegalParameterException {

        long tId = teamId.orElseThrow(IllegalParameterException::new);
        long uid = userId.orElseThrow(IllegalParameterException::new);

        this.teamRepository.addPlayerToTeam(tId, uid);

    }
}
