package com.vprp.user.utils;

import com.vprp.user.entity.UserSession;
import com.vprp.user.services.UserAuthService;
import com.vprp.user.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.vprp.user.entity.User;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    @Value("${jwt.token.validity}")
    public long TOKEN_VALIDITY;

    @Value("${jwt.session.validity}")
    public long SESSION_VALIDITY;

    @Value("${jwt.signing.key}")
    public String SIGNING_KEY;

    @Value("${jwt.authorities.key}")
    public String AUTHORITIES_KEY;

    @Autowired
    UserAuthService userAuthService;

    @Autowired
    UserServices userServices;

    // retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // generate token for user
    public String generateToken(User _user) {
        Map<String, Object> claims = new HashMap<>();
        // create user session
        UserSession session = this.createUserSession(_user.getLoginId(), Integer.parseInt(_user.getChannel()));
        claims.put("session_Id", session.getId());
        claims.put("sub", _user.getLoginId());
        return doGenerateToken(claims, _user.getLoginId());
    }

    // while creating the token -
    // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
    // 2. Sign the JWT using the HS512 algorithm and secret key.
    // 3. According to JWS Compact
    // Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    // compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        String authorities = userAuthService.loadUserByUsername(subject).getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

  

        return Jwts.builder()
                .setSubject(subject)
                .setClaims(claims)
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    // validate token
    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (Long.parseLong(username) == user.getId() && !isTokenExpired(token));
    }

    private UserSession createUserSession(String loginId, Integer channelId) {
        UserSession userSession = null;
        try {
            Date currentTimeStamp = new Date();
            userSession = new UserSession(loginId, channelId, currentTimeStamp, new Date(System.currentTimeMillis() + SESSION_VALIDITY * 60* 1000), 1, currentTimeStamp,currentTimeStamp);
           
        }catch (Exception e){
           e.printStackTrace();
        }
        return userServices.createUserSession(userSession);
    }
}
