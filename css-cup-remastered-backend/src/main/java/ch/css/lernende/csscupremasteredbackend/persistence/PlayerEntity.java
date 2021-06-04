package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "player", schema = "public")
public class PlayerEntity implements UserDetails, Serializable {

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
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private TeamEntity playerTeam;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RoleEntity playerRole;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DisciplineEntity playerDiscipline;

    private final boolean enabled = true;

    public PlayerEntity(
            String firstname,
            String lastname,
            String  function,
            String email,
            String password
    ) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.function = function;
        this.email = email;
        this.password = password;
    }

    public PlayerEntity() {

    }

    public TeamEntity getPlayerTeam() {
        return playerTeam;
    }

    public void setPlayerTeam(TeamEntity playerTeam) {
        this.playerTeam = playerTeam;
    }

    public RoleEntity getPlayerRole() {
        return playerRole;
    }

    public void setPlayerRole(RoleEntity playerRole) {
        this.playerRole = playerRole;
    }

    public DisciplineEntity getPlayerDiscipline() {
        return playerDiscipline;
    }

    public void setPlayerDiscipline(DisciplineEntity playerDiscipline) {
        this.playerDiscipline = playerDiscipline;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
