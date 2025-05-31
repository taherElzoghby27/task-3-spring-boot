package com.spring.boot.task3springboot.config;

import com.spring.boot.task3springboot.dto.AccountDto;
import com.spring.boot.task3springboot.dto.UserSecurityDto;
import com.spring.boot.task3springboot.service.AccountService;
import com.spring.boot.task3springboot.setting.JWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenHandler {
    private String secret;
    private Duration time;
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;
    @Autowired
    private AccountService accountService;

    public TokenHandler(JWTToken jwtToken) {
        this.secret = jwtToken.getSecret();
        this.time = jwtToken.getTime();
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtBuilder = Jwts.builder().signWith(key);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String generateToken(AccountDto accountDto) {
        this.jwtBuilder.setSubject(accountDto.getUsername());
        Date dateNow = new Date();
        this.jwtBuilder.setIssuedAt(dateNow);
        Date expiretionDate = createExpirationDate(dateNow);
        this.jwtBuilder.setExpiration(expiretionDate);
        this.jwtBuilder.claim("phoneNumber", accountDto.getPhoneNumber());
        return this.jwtBuilder.compact();
    }

    public AccountDto validateToken(String token) throws SystemException {

        try {
            if (this.jwtParser.isSigned(token)) {
                Claims claims = this.jwtParser.parseClaimsJws(token).getBody();
                String username = claims.getSubject();
                Date expirationDate = claims.getExpiration();
                Date issuedDate = claims.getIssuedAt();
                AccountDto user = accountService.getAccountByUserName(username);
                boolean isValidate = expirationDate.after(new Date()) && issuedDate.before(expirationDate) && Objects.nonNull(user);
                return isValidate ? user : null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new SystemException("something.wrong");
        }
        return null;
    }

    private Date createExpirationDate(Date date) {
        return Date.from(date.toInstant().plus(time));
    }
}
