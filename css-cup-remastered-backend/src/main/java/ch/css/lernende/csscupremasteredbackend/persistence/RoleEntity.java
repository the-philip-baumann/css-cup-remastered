package ch.css.lernende.csscupremasteredbackend.persistence;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rolle", schema = "public")
@Getter
@Setter
public class RoleEntity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "playerRole")
    private List<PlayerEntity> players = new ArrayList<>();
}
