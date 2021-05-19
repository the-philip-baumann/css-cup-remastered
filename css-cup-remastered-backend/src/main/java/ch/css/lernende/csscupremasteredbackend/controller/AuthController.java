package ch.css.lernende.csscupremasteredbackend.controller;

import ch.css.lernende.csscupremasteredbackend.dto.LoginDto;
import ch.css.lernende.csscupremasteredbackend.dto.RegisterDto;
import ch.css.lernende.csscupremasteredbackend.model.mapper.RegisterDtoToUserModel;
import ch.css.lernende.csscupremasteredbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        try {
            return ResponseEntity.ok(this.authService.register(RegisterDtoToUserModel.map(registerDto)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            return ResponseEntity.status(500).body("Failed to encrypt password. Please try later");
        }
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try {
            return ResponseEntity.ok(this.authService.login(loginDto.getEmail(), loginDto.getPassword()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Email and password do not match with any registered user");
        }
    }


}
