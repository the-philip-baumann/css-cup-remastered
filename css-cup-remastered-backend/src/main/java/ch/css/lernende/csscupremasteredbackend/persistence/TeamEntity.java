package ch.css.lernende.csscupremasteredbackend.persistence;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "team")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserEntity> players;

}
