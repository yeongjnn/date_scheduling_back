package com.example.date_scheduling.security;

import com.example.date_scheduling.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@Slf4j
//토큰 발행하는 클래스
public class TokenProvider {

    //우리 사이트의 서명(랜덤문자열 생성 - 512 바이트 이상)
    private static final String SECRET_KEY = "Z4NSl604sgyHJj1qwEkR3ycUeR4uUAt7WJraD7EN3O9DVM4yyYuHxMEbSF4XXyYJkal13eqgB0F7Bq4H";
    //서명이 바뀌면 안되니까 final로 설정

    //토큰 발행 메서드
    public String create(UserEntity userEntitiy) {
        //기한은 지금부터 1일로 설정
        Date expriyDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS) //시간 단위로도 할 수 있다
        );

        return Jwts.builder()
                //header에 들어갈 내용 및 서명을 위한 SECRET_KEY
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS512) //인코딩
                //payload에 들어갈 내용
                .setSubject(userEntitiy.getUsername()) //sub - 회원식별자
                .setIssuer("date_scheduling app") //iss - 토큰발행자 이름
                .setIssuedAt(new Date()) //iot - 토큰 발행일
                .setExpiration(expriyDate) //exp - 토큰 만료일
                .compact();

    }

    //토큰을 디코딩 및 파싱하고 트큰의 위조여부를 확인
    public String validateAndGetUserId(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())) //디코딩
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}

