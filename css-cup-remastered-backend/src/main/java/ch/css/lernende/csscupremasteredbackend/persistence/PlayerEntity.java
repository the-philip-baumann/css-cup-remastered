package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "player", schema = "public")
public class PlayerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "function", nullable = false)
    private String function;

    @Column(name = "email", nullable = false)
    private String email;

    //TODO: remove nullable
    @Column(name = "password")
    private byte[] password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TeamEntity playerTeam;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RoleEntity playerRole;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DisciplineEntity playerDiscipline;


    public PlayerEntity(
            String firstname,
            String lastname,
            String  function,
            String email,
            byte[] password
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.function = function;
        this.email = email;
        this.password = password;
    }

    public PlayerEntity() {

    }

}
