package ch.css.lernende.csscupremasteredbackend.repository.team;

import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<TeamEntity, Long>, CustomTeamRepository {

    TeamEntity findByName(String name);

    TeamEntity findById(long id);

    List<TeamEntity> findAll();

}
