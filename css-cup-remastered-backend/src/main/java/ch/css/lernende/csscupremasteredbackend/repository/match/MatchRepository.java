package ch.css.lernende.csscupremasteredbackend.repository.match;


import ch.css.lernende.csscupremasteredbackend.persistence.MatchEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MatchRepository extends CrudRepository<MatchEntity, Long> {
    List<MatchEntity> findAll();

    MatchEntity findAllById(long id);
}
