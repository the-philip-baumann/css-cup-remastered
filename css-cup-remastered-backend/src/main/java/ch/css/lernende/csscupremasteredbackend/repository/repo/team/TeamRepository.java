package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<TeamEntity, Long>, CustomTeamRepository {

    TeamEntity findByName(String name);

    TeamEntity findById(long id);

    List<TeamEntity> findAll();

}
