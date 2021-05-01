package ch.css.lernende.csscupremasteredbackend.persistence;

import ch.css.lernende.csscupremasteredbackend.model.Discipline;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "player")
public class UserEntity implements Serializable {

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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TeamEntity team;

    public UserEntity (
            String firstname,
            String lastname,
            String  function,
            String email,
            byte[] password,
            Role role,
            Discipline discipline
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.function = function;
        this.email = email;
        this.password = password;
        this.role = role;
        this.discipline = discipline;
    }

    public UserEntity () {

    }

}
