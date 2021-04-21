package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.UserEntity;
import ch.css.lernende.csscupremasteredbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthService() {
    }

    public String register(UserModel userModel) throws NoSuchAlgorithmException, InvalidKeySpecException {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstname(userModel.getFirstname());
        userEntity.setLastname(userModel.getLastname());
        userEntity.setFunction(userModel.getLastname());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setPassword(encryptPassword(userModel.getPassword()));
        userEntity.setRole(userModel.getRole());
        userEntity.setDiscipline(userModel.getDiscipline());
        UserEntity savedEntity = this.userRepository.save(userEntity);

        return generateAccessToken(savedEntity.getId(), savedEntity.getEmail(), savedEntity.getRole());
    }

    public String login(String email, String password) {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        return generateAccessToken(userEntity.getId(), userEntity.getEmail(), userEntity.getRole());
    }

    private boolean validatePassword(String password) {
        // TODO: Implement spring security
        return true;
    }


    private byte[] encryptPassword(String plain) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // TODO: Implement spring security
       return plain.getBytes();
    }

    private String generateAccessToken(long id, String email, Role role) {
        // TODO: Implement JWT strategy
        return "JWT-TEMPORARY-TOKEN";
    }

}
