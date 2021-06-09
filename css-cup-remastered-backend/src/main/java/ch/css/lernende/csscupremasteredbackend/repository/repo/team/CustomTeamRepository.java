package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import java.sql.SQLException;

public interface CustomTeamRepository {
    void insertTeam(String name, Discipline discipline, PlayerEntity captain) throws SQLException;
    void addPlayerToTeam(long userId, long teamId);
}
