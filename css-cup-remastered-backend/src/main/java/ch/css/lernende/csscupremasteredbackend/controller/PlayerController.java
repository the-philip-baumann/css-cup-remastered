package ch.css.lernende.csscupremasteredbackend.controller;

import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPlayer () {
        try {
            return ResponseEntity.ok(this.playerService.getAllPlayers());
        } catch (NoResultsFoundException e) {
            return ResponseEntity.status(405).body("No Results Found");
        }
    }

    @GetMapping(path = "/solo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPlayersWithoutTeam() {
        try {
            return ResponseEntity.ok(this.playerService.getAllPlayersWithoutTeam());
        } catch (Exception e) {
            return ResponseEntity.status(405).body(e.getMessage());
        }
    }

//    // TODO: Remove
//    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity getSinglePlayer(@PathVariable Optional<Long> id) {
//        try {
//            return ResponseEntity.ok(playerService.getSinglePlayer(id));
//        } catch (IllegalParameterException | NoResultsFoundException e) {
//            return ResponseEntity.status(400).body("No Results found");
//        }
//    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteSinglePlayer(@PathVariable Optional<Long> id) {
        try {
            playerService.deleteSinglePlayer(id);
            return ResponseEntity.ok("User deleted");
        } catch (IllegalParameterException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User with id: " + id.get() + " could not be deleted.");
        }
    }

//    @PutMapping(path = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity editPlayer(@PathVariable Optional<Long> id, @RequestBody Optional<PlayerDto> playerDto) {
//        try {
//            playerService.editPlayer(id, playerDto);
//            return ResponseEntity.ok().build();
//        } catch (IllegalParameterException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

}
