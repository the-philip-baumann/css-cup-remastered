package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void insertTeam(String name, DisciplineEntity discipline) throws IllegalParameterException {
        TeamEntity teamEntity = new TeamEntity();
        if (!isTeamFull(teamEntity)) {
            return;
        }

        if (doesTeamAlreadyExistWithName(name)) {
            return;
        }

        teamEntity.setName(name);
        teamEntity.setTeamDiscipline(discipline);
        entityManager.persist(teamEntity);
    }

    @Override
    @Transactional
    public void addPlayerToTeam(long userId, long teamId) throws IllegalParameterException {
        TeamEntity teamEntity = this.entityManager.find(TeamEntity.class, teamId);

        if (!isTeamFull(teamEntity)) {
            return;
        }

        PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class, userId);
        playerEntity.setPlayerTeam(teamEntity);
        this.entityManager.persist(playerEntity);
    }

    private boolean doesTeamAlreadyExistWithName(String name) throws IllegalParameterException {
        List<TeamEntity> teams = entityManager
                .createQuery("SELECT team FROM TeamEntity team", TeamEntity.class)
                .getResultList();

        List<TeamEntity> unique = teams.stream().filter(team ->
                team.getName().equals(name)
        ).collect(Collectors.toList());

        if (unique.size() > 0) {
            throw new IllegalParameterException();
        }
        return false;
    }

    private boolean isTeamFull(TeamEntity teamEntity) throws IllegalParameterException {
        if (teamEntity.getPlayers().size() >= 10) {
            throw new IllegalParameterException();
        }
        return true;
    }
}
