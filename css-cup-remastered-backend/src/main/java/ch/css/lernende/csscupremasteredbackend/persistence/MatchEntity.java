package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "match")
@Getter
@Setter
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "field")
    private int field;

    @OneToMany(mappedBy = "team")
    private List<TeamEntity> teams;
}
