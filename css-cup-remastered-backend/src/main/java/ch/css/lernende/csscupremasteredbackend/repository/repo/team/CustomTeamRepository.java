package ch.css.lernende.csscupremasteredbackend.repository.repo.team;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;

public interface CustomTeamRepository {
    void insertTeam(String name, Discipline discipline);
}
