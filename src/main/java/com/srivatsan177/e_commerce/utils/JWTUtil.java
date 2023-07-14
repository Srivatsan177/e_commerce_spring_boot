package com.srivatsan177.e_commerce.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtil {
    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret}")
    private String jwtSecret;

    Algorithm algorithm;

    JWTVerifier verifier;

    private void setAlgorithmIfNotExists() {
        if (algorithm == null){
            algorithm = Algorithm.HMAC256(jwtSecret);
        }
    }

    private void setVerifierIfNotExists() {
        if(verifier == null) {
            verifier = JWT.require(algorithm).withIssuer(issuer).build();
        }
    }

    public String getJWTToken(String username) {
        setAlgorithmIfNotExists();
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(issuer)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + (long) (8 * 60 * 60 * 1000)))
                .sign(algorithm)
                ;
    }

    private DecodedJWT verifyJWTToken(String jwt) throws JWTVerificationException {
        setAlgorithmIfNotExists();
        setVerifierIfNotExists();
        return verifier.verify(jwt);
    }

    public String getUserClaim(String jwt) throws JWTVerificationException {
        setAlgorithmIfNotExists();
        setVerifierIfNotExists();
        try{
            DecodedJWT decodedJWT = verifyJWTToken(jwt);
            return decodedJWT.getClaim("username").asString();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("jwt token verification failed");
        }
    }
}
