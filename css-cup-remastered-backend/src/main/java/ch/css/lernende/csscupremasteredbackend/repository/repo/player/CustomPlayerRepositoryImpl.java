package ch.css.lernende.csscupremasteredbackend.repository.repo.player;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.RoleEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.TeamEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.discipline.DisciplineRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomPlayerRepositoryImpl implements CustomPlayerRepository {

    private final DisciplineRepository disciplineRepository;
    private final RoleRepository roleRepository;
    private final EntityManager entityManager;

    @Autowired
    public CustomPlayerRepositoryImpl(DisciplineRepository disciplineRepository, RoleRepository roleRepository, EntityManager entityManager) {
        this.disciplineRepository = disciplineRepository;
        this.roleRepository = roleRepository;
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List findAllPlayersWithoutTeam() {
        Query query = entityManager.createQuery("SELECT p from PlayerEntity p WHERE p.playerTeam IS NULL");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void addTeamToPlayer(TeamEntity teamEntity, long userId) {
        PlayerEntity playerEntity = this.entityManager.find(PlayerEntity.class, userId);
        playerEntity.setPlayerTeam(teamEntity);
        this.entityManager.persist(playerEntity);
    }

    @Override
    @Transactional
    public PlayerEntity insertPlayer(UserModel userModel) throws IllegalParameterException {
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
        return this.entityManager.merge(playerEntity);
    }
}
