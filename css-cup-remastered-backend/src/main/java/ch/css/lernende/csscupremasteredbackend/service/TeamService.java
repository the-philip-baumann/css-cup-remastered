package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import ch.css.lernende.csscupremasteredbackend.model.mapper.TeamMapper;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.UserEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.team.TeamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> fetchAll() {
        return TeamMapper.teamEntitiesToTeamDtos(teamRepository.findAll());
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

    public void addTeam(TeamModel teamModel) {
        //TODO: Remove teamp captain and implement real user

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname("Philip");
        userEntity.setLastname("Baumann");
        userEntity.setDiscipline(Discipline.FOOTBALL);
        userEntity.setRole(Role.ADMIN);
        userEntity.setEmail("philip.baumann@hispeed.ch");
        userEntity.setFunction("IEL*");

        teamRepository.insertTeam(teamModel.getName(), teamModel.getDiscipline(), userEntity);
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
}