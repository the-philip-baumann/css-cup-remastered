package ch.css.lernende.csscupremasteredbackend.repository.repo.user;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {
        PlayerEntity findByEmail(String email);
        List<PlayerEntity> findAll();
}
