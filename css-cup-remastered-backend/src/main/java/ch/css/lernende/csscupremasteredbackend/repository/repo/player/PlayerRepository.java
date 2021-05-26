package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long>, CustomPlayerRepository {
        PlayerEntity findByEmail(String email);
        List<PlayerEntity> findAll();
}
