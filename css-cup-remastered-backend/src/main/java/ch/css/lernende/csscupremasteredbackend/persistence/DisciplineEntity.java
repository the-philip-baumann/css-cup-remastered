package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "discipline", schema = "public")
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "playerDiscipline")
    private List<PlayerEntity> players;

    @OneToMany(mappedBy = "teamDiscipline")
    private List<TeamEntity> teams;
}
