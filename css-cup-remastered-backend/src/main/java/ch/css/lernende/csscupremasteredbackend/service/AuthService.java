package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.RoleEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.discipline.DisciplineRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

@Service
public class AuthService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final DisciplineRepository disciplineRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, DisciplineRepository disciplineRepository) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.disciplineRepository = disciplineRepository;

    }

    public String register(UserModel userModel) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalParameterException, SQLException {
        userModel.setPassword(encryptPassword(userModel.getPassword()));
        PlayerEntity savedEntity = playerRepository.insertPlayer(userModel);
        return generateAccessToken(savedEntity.getId(), savedEntity.getEmail());
    }

    public String login(String email, String password) throws IllegalParameterException {
        PlayerEntity playerEntity = this.playerRepository.findByEmail(email).orElseThrow(IllegalParameterException::new);
        return generateAccessToken(playerEntity.getId(), playerEntity.getEmail());
    }

    private boolean validatePassword(String password) {
        // TODO: Implement spring security

        return true;
    }


    private String encryptPassword(String plain) {
        String encryptedPassword = passwordEncoder.encode(plain);
        System.out.println("encryptedPassword = " + encryptedPassword);
        return encryptedPassword;
    }

    private String generateAccessToken(long id, String email) {
        // TODO: Implement JWT strategy
        return "JWT-TEMPORARY-TOKEN";
    }

}
