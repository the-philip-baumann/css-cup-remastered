package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;

import java.sql.SQLException;

public interface CustomTeamRepository {
    void insertTeam(String name, DisciplineEntity discipline) throws SQLException, IllegalParameterException;
    void addPlayerToTeam(long userId, long teamId) throws IllegalParameterException;
}
