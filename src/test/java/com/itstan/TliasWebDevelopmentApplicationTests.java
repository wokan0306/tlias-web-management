package com.itstan;

import com.itstan.controller.DeptController;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.catalina.core.ApplicationContext;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebDevelopmentApplicationTests {


    @Test
    void testUuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    @Test
    public void testGenJwt() {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "itstanTliasWebDevelopmentApplicationTests07052024") // sign algorithm
                .setClaims(claims) // self-defined content
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .compact();
        System.out.println(jwt);

    }

    @Test
    public void testPraseJwt() {
         String claims = Jwts.parser()
                .setSigningKey("itstanTliasWebDevelopmentApplicationTests07052024")
                .build()
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcxNTA1Mzc4OX0.kp-jH7DhAPtUrlkWg-NJpt12o4aujjiXgDLBcE4k3Ls")
                .getBody()
                 .toString();
        System.out.println(claims);

    }



}
