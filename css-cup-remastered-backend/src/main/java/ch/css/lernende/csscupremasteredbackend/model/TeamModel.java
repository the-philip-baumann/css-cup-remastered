package ch.css.lernende.csscupremasteredbackend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TeamModel {

    private String name;
    private Discipline discipline;
    private List<PlayerModel> players;

}
