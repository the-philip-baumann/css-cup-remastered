package ch.css.lernende.csscupremasteredbackend.util;

import ch.css.lernende.csscupremasteredbackend.dto.PlayerDto;
import ch.css.lernende.csscupremasteredbackend.exception.UnauthorizedException;
import ch.css.lernende.csscupremasteredbackend.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtTokenUtil {
    public String getEmail(String token) {
        return null;
    }

    private static final String SECRET_KEY = "secret";
    private static final String ISSUER = "Cinegame-Backlog";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);

    public DecodedJWT verifyToken(String token) throws UnauthorizedException {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .acceptExpiresAt(3600)
                    .withIssuer(ISSUER)
                    .acceptLeeway(1).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new UnauthorizedException();
        }
    }

    public String createToken(PlayerDto playerDto) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        return JWT.create()
                .withIssuer(ISSUER)
                .withClaim("username", playerDto.getEmail())
                .withClaim("expiryDate", calendar.getTime())
                .withClaim("authority", new ArrayList<>())
                .sign(ALGORITHM);
    }
}
