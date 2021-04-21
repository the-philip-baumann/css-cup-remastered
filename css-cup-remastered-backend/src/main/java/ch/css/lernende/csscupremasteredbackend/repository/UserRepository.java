package ch.css.lernende.csscupremasteredbackend.repository;

import ch.css.lernende.csscupremasteredbackend.persistence.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
        UserEntity findByEmail(String email);
}
