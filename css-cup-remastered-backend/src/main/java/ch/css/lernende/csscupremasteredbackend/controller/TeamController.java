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

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAllTeams() {
        List<TeamDto> teams = teamService.fetchAll();
        return ResponseEntity.ok(teams);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchTeam(@PathVariable Optional<Long> id) {
        try {
            TeamDto teamDto = teamService.fetchTeam(id);
            return ResponseEntity.ok(teamDto);
        } catch (NoResultsFoundException e) {
            return ResponseEntity.ok("No Results found for id: " + id);
        } catch (IllegalParameterException e) {
            return ResponseEntity.status(400).body("Bad request");
        }
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTeam(@RequestBody AddTeamDto addTeamDto) {
        TeamModel teamModel = AddTeamDtoToTeamModel.map(addTeamDto);

        teamService.addTeam(teamModel);
        return ResponseEntity.ok("Team was created");
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity deleteTeam(@PathVariable Optional<Long> id) {
        teamService.deleteTeam(id);
        return ResponseEntity.ok("Team was deleted");
    }

    @PostMapping(path = "/rename", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity renameTeam(@RequestBody RenameTeamDto renameTeam) {
        System.out.println(renameTeam.getName());
        teamService.renameTeam(renameTeam.getId(), renameTeam.getName());
        return ResponseEntity.ok("Team was renamed to: " + renameTeam.getName());
    }



}