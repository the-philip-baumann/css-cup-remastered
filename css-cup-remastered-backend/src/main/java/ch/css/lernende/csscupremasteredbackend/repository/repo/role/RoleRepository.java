package ch.css.lernende.csscupremasteredbackend.repository.repo.role;

import ch.css.lernende.csscupremasteredbackend.persistence.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
}
