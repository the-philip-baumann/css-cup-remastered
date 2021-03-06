package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.dto.PlayerTeamRoleDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.exception.NoResultsFoundException;
import ch.css.lernende.csscupremasteredbackend.model.mapper.PlayerMapper;
import ch.css.lernende.csscupremasteredbackend.persistence.PlayerEntity;
import ch.css.lernende.csscupremasteredbackend.repository.repo.player.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<PlayerTeamRoleDto> getAllPlayers() throws NoResultsFoundException {
        List<PlayerEntity> playerEntities = playerRepository.findAll();
        if (playerEntities.isEmpty()) {
            throw new NoResultsFoundException();
        }

        return PlayerMapper.listPlayerEntityToPlayerTeamRoleDto(playerEntities);
    }

    public void deleteSinglePlayer(Optional<Long> id) throws IllegalParameterException {
        long userId = id.orElseThrow(IllegalParameterException::new);
        playerRepository.deleteById(userId);
    }

    public List<PlayerDto> getAllPlayersWithoutTeam() {
        List<PlayerEntity> playerEntities = this.playerRepository.findAllPlayersWithoutTeam();
        return PlayerMapper.listUserEntityToListUserEntity(playerEntities);
    }
}
