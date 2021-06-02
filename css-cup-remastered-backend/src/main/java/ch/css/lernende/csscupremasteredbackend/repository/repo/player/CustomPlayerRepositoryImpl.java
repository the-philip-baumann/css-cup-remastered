package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Transactional
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    private final EntityManager entityManager;

    @Override
    public List findAllPlayersWithoutTeam() {
        Query query = entityManager.createQuery("SELECT p from PlayerEntity p WHERE p.playerTeam IS NULL");
        return query.getResultList();
    }

    @Override
    public void addTeamToPlayer(TeamEntity teamEntity, long userId) {
        PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class, userId);
        playerEntity.setPlayerTeam(teamEntity);
        this.entityManager.persist(playerEntity);
    }
}
