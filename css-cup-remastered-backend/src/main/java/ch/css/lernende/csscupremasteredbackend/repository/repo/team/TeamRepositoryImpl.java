package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Collections;

@AllArgsConstructor
@Transactional
public class TeamRepositoryImpl implements CustomTeamRepository {

    private final EntityManager entityManager;

    @Override
    public void insertTeam(String name, Discipline discipline, PlayerEntity captain) {
        TeamEntity teamEntity = new TeamEntity();
        System.out.println(name);
        System.out.println(discipline);
        teamEntity.setName(name);
        teamEntity.setPlayers(Collections.singletonList(captain));
        entityManager.persist(teamEntity);

    }
}
