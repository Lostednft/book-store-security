package com.example.book_store_security.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.book_store_security.domains.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${secret.key.api.token}")
    private String secretKey;

    public String generate(Users users){

        var algorithm = Algorithm.HMAC256(secretKey);

        try{
            String token = JWT.create()
                    .withIssuer("LuisFelipe")
                    .withSubject(users.getLogin())
                    .withExpiresAt(genExperiesToken())
                    .sign(algorithm);
            return token;
        }
        catch (JWTCreationException e){
            throw new RuntimeException("Erro While Generating Token", e);
        }
    }

    public Instant genExperiesToken(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String tokenValidate)
    {
        try {
            var algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("LuisFelipe")
                    .build()
                    .verify(tokenValidate)
                    .getSubject();
        }
        catch (JWTVerificationException exception)
        {
            throw new RuntimeException("Error While Verification of token", exception);
        }
    }
}
