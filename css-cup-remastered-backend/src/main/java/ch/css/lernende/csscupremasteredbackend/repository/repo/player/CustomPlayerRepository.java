package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;

import java.util.List;

public interface CustomPlayerRepository {
    List<PlayerEntity> findAllPlayersWithoutTeam();
    void addTeamToPlayer(TeamEntity teamEntity, long userId);
}
