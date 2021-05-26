package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import java.util.List;

public interface CustomPlayerRepository {
    List<PlayerEntity> findAllPlayersWithoutTeam();
}
