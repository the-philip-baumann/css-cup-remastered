package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.PlayerModel;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;

import java.sql.SQLException;
import java.util.List;

public interface CustomPlayerRepository {
    List<PlayerEntity> findAllPlayersWithoutTeam();
    void addTeamToPlayer(TeamEntity teamEntity, long userId) throws SQLException;
    PlayerEntity insertPlayer(UserModel playerModel) throws IllegalParameterException, SQLException;
}
