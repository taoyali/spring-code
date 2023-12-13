package com.tyl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.*;

public class JwtTest {

    @Test
    public void testJWTGen() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "tty");
        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1001 * 3600 * 12))
                .sign(Algorithm.HMAC256("tty"));

        System.out.println(token);
    }

    @Test
    public void testJWTParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6InR0eSJ9LCJleHAiOjE3MDI1MDg1Mzd9.PdO-iFDAm9uoWp1vef4hAPg0j0eIMKYo91BB_q47JZE";
        JWTVerifier tty = JWT.require(Algorithm.HMAC256("tty")).build();
        DecodedJWT verify = tty.verify(token);
        Map<String, Claim> claims = verify.getClaims();
        System.out.println(claims);
    }
}
