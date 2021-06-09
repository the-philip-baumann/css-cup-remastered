package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long>, CustomPlayerRepository {
        Optional<PlayerEntity> findByEmail(String email);
        List<PlayerEntity> findAll();
}
