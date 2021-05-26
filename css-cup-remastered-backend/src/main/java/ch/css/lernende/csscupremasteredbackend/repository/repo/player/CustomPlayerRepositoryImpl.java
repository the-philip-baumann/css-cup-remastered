package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

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
}
