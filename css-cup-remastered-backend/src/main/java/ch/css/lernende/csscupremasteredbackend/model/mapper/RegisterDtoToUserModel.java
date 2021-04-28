package ch.css.lernende.csscupremasteredbackend.model.mapper;

import ch.css.lernende.csscupremasteredbackend.dto.RegisterDto;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;

public class RegisterDtoToUserModel {

    public static UserModel map(RegisterDto registerDto) {
        return new UserModel(
                registerDto.getFirstname(),
                registerDto.getLastname(),
                registerDto.getFunction(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                registerDto.getRole(),
                registerDto.getDiscipline()
        );
    }

}
