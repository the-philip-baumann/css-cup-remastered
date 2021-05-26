package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
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

    public List<PlayerDto> getAllPlayers() throws NoResultsFoundException {
        List<PlayerEntity> playerEntities = playerRepository.findAll();
        if (playerEntities.isEmpty()) {
            throw new NoResultsFoundException();
        }

        return PlayerMapper.listUserEntityToListUserEntity(playerEntities);
    }

    public PlayerDto getSinglePlayer(Optional<Long> id) throws NoResultsFoundException, IllegalParameterException {
        long userId = id.orElseThrow(IllegalParameterException::new);
        Optional<PlayerEntity> playerEntity = playerRepository.findById(userId);
        if (playerEntity.isEmpty()) {
            throw new NoResultsFoundException();
        }

        return PlayerMapper.userEntityToUserDto(playerEntity.get());
    }

    public void deleteSinglePlayer(Optional<Long> id) throws IllegalParameterException {
        long userId = id.orElseThrow(IllegalParameterException::new);
        playerRepository.deleteById(userId);
    }

    public void editPlayer(Optional<Long> id, Optional<PlayerDto> playerDto) throws IllegalParameterException {
        if (id.isEmpty() || playerDto.isEmpty()) {
            throw new IllegalParameterException();
        }

        //TODO: Implement edit player
    }

    public List<PlayerDto> getAllPlayersWithoutTeam() {
        List<PlayerEntity> playerEntities = this.playerRepository.findAllPlayersWithoutTeam();
        return PlayerMapper.listUserEntityToListUserEntity(playerEntities);
    }
}
