package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import ch.css.lernende.csscupremasteredbackend.model.mapper.PlayerMapper;
import ch.css.lernende.csscupremasteredbackend.persistence.DisciplineEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.persistence.RoleEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.discipline.DisciplineRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import ch.css.lernende.csscupremasteredbackend.repository.repo.role.RoleRepository;
import ch.css.lernende.csscupremasteredbackend.util.JwtTokenUtil;
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
    private final JwtTokenUtil jwtTokenUtil;
    private final DisciplineRepository disciplineRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthService(PlayerRepository playerRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, DisciplineRepository disciplineRepository, JwtTokenUtil jwtTokenUtil) {
        this.playerRepository = playerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.disciplineRepository = disciplineRepository;
        this.jwtTokenUtil = jwtTokenUtil;

    }

    public String register(UserModel userModel) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalParameterException, SQLException {
        userModel.setPassword(encryptPassword(userModel.getPassword()));
        PlayerEntity savedEntity = playerRepository.insertPlayer(userModel);
        PlayerDto playerDto = PlayerMapper.userEntityToUserDto(savedEntity);
        return jwtTokenUtil.createToken(playerDto);
    }

    private String encryptPassword(String plain) {
         return passwordEncoder.encode(plain);
    }
}
