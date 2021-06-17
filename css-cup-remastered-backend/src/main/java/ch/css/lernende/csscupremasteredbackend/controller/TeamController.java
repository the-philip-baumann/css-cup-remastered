package ch.css.lernende.csscupremasteredbackend.controller;

import ch.css.lernende.csscupremasteredbackend.dto.AddTeamDto;
import ch.css.lernende.csscupremasteredbackend.dto.RenameTeamDto;
import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.model.TeamModel;
import ch.css.lernende.csscupremasteredbackend.model.mapper.AddTeamDtoToTeamModel;
import ch.css.lernende.csscupremasteredbackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAllTeams() {
        List<TeamDto> teams = teamService.fetchAll();
        return ResponseEntity.ok(teams);
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTeam(@RequestBody AddTeamDto addTeamDto) {
        try {
            teamService.addTeam(addTeamDto);
            return ResponseEntity.ok().build();
        } catch (IllegalParameterException | SQLException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable Optional<Long> id) {
        try {
            teamService.deleteTeam(id);
            return ResponseEntity.ok("Team was deleted");
        } catch (IllegalParameterException e) {
            return ResponseEntity.status(400).body("Bad Request");
        }
    }

    @PostMapping(path = "/{teamId}/join/{userId}")
    public ResponseEntity joinTeam(@PathVariable Optional<Long> teamId, @PathVariable Optional<Long> userId) {
        try {
            // TODO: Implement proper user Id
            teamService.joinTeam(userId, teamId);
            return ResponseEntity.ok().build();
        } catch (IllegalParameterException e) {
            return ResponseEntity.status(500).body("IllegalParameterException");
        }
    }
}
