package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;

@AllArgsConstructor
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public void insertTeam(String name, DisciplineEntity discipline) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(name);
        teamEntity.setTeamDiscipline(discipline);
        entityManager.persist(teamEntity);
    }

    @Override
    @Transactional
    public void addPlayerToTeam(long userId, long teamId) {
        TeamEntity teamEntity = this.entityManager.find(TeamEntity.class, teamId);
        PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class, userId);
        playerEntity.setPlayerTeam(teamEntity);
        this.entityManager.persist(playerEntity);
    }
}
