package ch.css.lernende.csscupremasteredbackend.controller;

import ch.css.lernende.csscupremasteredbackend.dto.AuthStateDto;
import ch.css.lernende.csscupremasteredbackend.dto.LoginDto;
import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.dto.RegisterDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.PlayerModel;
import ch.css.lernende.csscupremasteredbackend.model.Role;
import ch.css.lernende.csscupremasteredbackend.model.mapper.PlayerMapper;
import ch.css.lernende.csscupremasteredbackend.model.mapper.RegisterDtoToUserModel;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.service.AuthService;
import ch.css.lernende.csscupremasteredbackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Set;

import javax.validation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private Validator validator;

    @Autowired
    public AuthController(AuthService authService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthStateDto> register(@RequestBody @Valid RegisterDto registerDto) {
        try {
            String jwt = this.authService.register(RegisterDtoToUserModel.map(registerDto));
            return ResponseEntity.ok(new AuthStateDto(jwt, null));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IllegalParameterException | SQLException e) {
            return ResponseEntity.status(500).body(new AuthStateDto(null, "Invalid Attempt To Sign In"));
        }
    }

    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AuthStateDto> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            PlayerDto playerDto = PlayerMapper.userEntityToUserDto((PlayerEntity) authentication.getPrincipal());
            String jwt = jwtTokenUtil.createToken(playerDto);

            return ResponseEntity.ok().body(new AuthStateDto(jwt, null));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new AuthStateDto(null, e.getMessage()));
        }
    }


}
