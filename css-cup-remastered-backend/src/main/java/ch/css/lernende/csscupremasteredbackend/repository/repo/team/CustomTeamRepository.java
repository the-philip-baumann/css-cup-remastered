package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.persistence.UserEntity;

public interface CustomTeamRepository {
    void insertTeam(String name, Discipline discipline, UserEntity captain);
}
