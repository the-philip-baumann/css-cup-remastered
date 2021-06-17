package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "team", schema = "public")
public class TeamEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private DisciplineEntity teamDiscipline;

    @OneToMany(mappedBy = "playerTeam", fetch = FetchType.EAGER)
    private List<PlayerEntity> players = new ArrayList<>();
}
