package ch.css.lernende.csscupremasteredbackend.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class MatchDto {

    private long id;
    private LocalDateTime time;
    private int field;
    private List<TeamDto> teams;

}
