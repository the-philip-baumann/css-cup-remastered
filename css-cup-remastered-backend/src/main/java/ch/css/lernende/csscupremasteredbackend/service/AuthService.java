package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class AuthService {

    private final PlayerRepository playerRepository;

    @Autowired
    public AuthService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public String register(UserModel userModel) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setFirstname(userModel.getFirstname());
        playerEntity.setLastname(userModel.getLastname());
        playerEntity.setFunction(userModel.getLastname());
        playerEntity.setEmail(userModel.getEmail());
        playerEntity.setPassword(encryptPassword(userModel.getPassword()));
        PlayerEntity savedEntity = this.playerRepository.save(playerEntity);

        return generateAccessToken(savedEntity.getId(), savedEntity.getEmail());
    }

    public String login(String email, String password) {
        PlayerEntity playerEntity = this.playerRepository.findByEmail(email);
        return generateAccessToken(playerEntity.getId(), playerEntity.getEmail());
    }

    private boolean validatePassword(String password) {
        // TODO: Implement spring security
        return true;
    }


    private byte[] encryptPassword(String plain) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // TODO: Implement spring security
       return plain.getBytes();
    }

    private String generateAccessToken(long id, String email) {
        // TODO: Implement JWT strategy
        return "JWT-TEMPORARY-TOKEN";
    }

}
