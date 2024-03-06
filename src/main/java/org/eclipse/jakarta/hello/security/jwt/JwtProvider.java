package org.eclipse.jakarta.hello.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.eclipse.jakarta.hello.base.exception.UnauthorizedException;
import org.eclipse.jakarta.hello.security.tokenprovider.TokenProvider;

import javax.ejb.Stateless;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class JwtProvider implements TokenProvider {

    private final Algorithm algorithm = Algorithm.HMAC256("super-secret");

    private final String issuer = "personal-project";

    @Override
    public String generateToken(Map<String, String> payload) {
        String token;
        try {
            JWTCreator.Builder jwtBuilder = JWT.create();

            jwtBuilder.withPayload(payload);
            jwtBuilder.withIssuer(issuer);
            jwtBuilder.withExpiresAt(new Date(System.currentTimeMillis() + 10800000));

            token = jwtBuilder.sign(algorithm);

            return token;
        }
        catch (JWTCreationException jwtCreationException) {
            throw new ServerErrorException("Failed to create token", Response.Status.INTERNAL_SERVER_ERROR, jwtCreationException);
        }
    }

    @Override
    public Map<String, String> validateToken(String token) throws UnauthorizedException {
        DecodedJWT decodedJWT;
        try {
            JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(issuer).build();
            decodedJWT = jwtVerifier.verify(token);
        }
        catch (JWTVerificationException exception) {
            throw new UnauthorizedException("Invalid token");
        }
        Map<String, String> result = new HashMap<>();
        decodedJWT.getClaims().keySet().forEach(key -> {
            result.put(key, decodedJWT.getClaim(key).asString());
        });

        return result;
    }
}
