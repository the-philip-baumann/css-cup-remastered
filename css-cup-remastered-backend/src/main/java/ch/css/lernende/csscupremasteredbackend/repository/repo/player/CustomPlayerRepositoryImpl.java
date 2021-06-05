package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.RoleEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.discipline.DisciplineRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Transactional
public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    private final DisciplineRepository disciplineRepository;
    private final RoleRepository roleRepository;
    private final PlayerRepository playerRepository;

    private EntityManager entityManager;

    @Autowired
    public CustomPlayerRepositoryImpl(DisciplineRepository disciplineRepository, RoleRepository roleRepository, PlayerRepository playerRepository) {
        this.disciplineRepository = disciplineRepository;
        this.roleRepository = roleRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List findAllPlayersWithoutTeam() {
        Query query = entityManager.createQuery("SELECT p from PlayerEntity p WHERE p.playerTeam IS NULL");
        return query.getResultList();
    }

    @Override
    public void addTeamToPlayer(TeamEntity teamEntity, long userId) throws SQLException {
        try {
            this.entityManager.getTransaction().begin();
            PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class, userId);
            playerEntity.setPlayerTeam(teamEntity);
            this.entityManager.persist(playerEntity);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            this.entityManager.close();
            throw new SQLException();
        }

    }

    @Override
    public PlayerEntity insertPlayer(UserModel userModel) throws SQLException {
        try {
            this.entityManager.getTransaction().begin();
            PlayerEntity playerEntity = new PlayerEntity();
            playerEntity.setFirstname(userModel.getFirstname());
            playerEntity.setLastname(userModel.getLastname());
            playerEntity.setFunction(userModel.getLastname());
            playerEntity.setEmail(userModel.getEmail());
            RoleEntity roleEntity = roleRepository.findByName(userModel.getRole()).orElseThrow(IllegalParameterException::new);
            DisciplineEntity disciplineEntity = disciplineRepository.findByName(userModel.getDiscipline()).orElseThrow(IllegalParameterException::new);
            playerEntity.setPlayerRole(roleEntity);
            playerEntity.setPlayerDiscipline(disciplineEntity);
            playerEntity.setPassword(userModel.getPassword());
            PlayerEntity savedEntity = this.entityManager.merge(playerEntity);
            return savedEntity;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            this.entityManager.close();
            throw new SQLException();
        }
    }
}
