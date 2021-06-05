package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Collections;

@AllArgsConstructor
@Transactional
public class CustomTeamRepositoryImpl implements CustomTeamRepository {

    private final EntityManager entityManager;

    @Override
    public void insertTeam(String name, Discipline discipline, PlayerEntity captain) throws SQLException {
        try {
            this.entityManager.getTransaction().begin();
            TeamEntity teamEntity = new TeamEntity();
            teamEntity.setName(name);
            teamEntity.setPlayers(Collections.singletonList(captain));
            entityManager.persist(teamEntity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            throw new SQLException();
        }

    }

    @Override
    public void addPlayerToTeam(long userId, long teamId) {
        try {
            this.entityManager.getTransaction().begin();
            TeamEntity teamEntity = this.entityManager.find(TeamEntity.class, teamId);
            PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class,userId);
            playerEntity.setPlayerTeam(teamEntity);
            this.entityManager.persist(playerEntity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
        }

    }
}
