package ch.css.lernende.csscupremasteredbackend.repository.repo;

import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisciplineRepository extends CrudRepository<DisciplineEntity, Long> {
    Optional<DisciplineEntity> findByName(String name);
}
