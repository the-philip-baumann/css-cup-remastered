package ch.css.lernende.csscupremasteredbackend.service;

import ch.css.lernende.csscupremasteredbackend.dto.MatchDto;
import ch.css.lernende.csscupremasteredbackend.dto.TeamDto;
import ch.css.lernende.csscupremasteredbackend.exception.IllegalParameterException;
import ch.css.lernende.csscupremasteredbackend.model.mapper.MatchMapper;
import ch.css.lernende.csscupremasteredbackend.repository.match.MatchRepository;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<MatchDto> getAllMatches() {
        return MatchMapper.matchEntitiesToMatchDtos(matchRepository.findAll());
    }

    public MatchDto getSingleMatch(Optional<Long> id) throws IllegalParameterException {
        long matchId = id.orElseThrow(IllegalParameterException::new);
        return MatchMapper.matchEntityToMatchDto(matchRepository.findAllById(matchId));
    }

}
