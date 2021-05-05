package ch.css.lernende.csscupremasteredbackend.repository.repo.user;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
        PlayerEntity findByEmail(String email);
        List<PlayerEntity> findAll();
}
