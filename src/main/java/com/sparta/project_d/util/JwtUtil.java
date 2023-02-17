//package com.sparta.project_d.util;
//
//import com.sparta.project_d.dto.FactoryDto;
//import com.sparta.project_d.dto.ItemsListDto;
//import com.sparta.project_d.dto.MemberDto;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import io.jsonwebtoken.security.SecurityException;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class JwtUtil {
//
//    public static final String AUTHORIZATION_HEADER = "Authorization";
//    public static final String AUTHORIZATION_KEY = "auth";
//    private static final String BEARER_PREFIX = "Bearer ";
//    private static final long TOKEN_TIME = 60 * 60 * 1000L;
//
//    @Value("${jwt.secret.key}")
//    @Getter
//    private String secretKey;
//    private Key key;
//    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//    @PostConstruct
//    public void init() {
//        byte[] bytes = Base64.getDecoder().decode(secretKey);
//        key = Keys.hmacShaKeyFor(bytes);
//    }
//
//    // header 토큰을 가져오기
//    public String resolveToken(HttpServletRequest request) {
//        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    // 토큰 생성
//    public String createToken(MemberDto member, ItemsListDto itemsListDto, FactoryDto factoryDto) {
//        Date date = new Date();
//
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("member", member);
//        claims.put("itemsDto", itemsListDto);
//        claims.put("factoryDto", factoryDto);
//
//        return BEARER_PREFIX +
//                Jwts.builder()
//                        .addClaims(claims)
//                        .setExpiration(new Date(date.getTime() + TOKEN_TIME))
//                        .setIssuedAt(date)
//                        .signWith(key, signatureAlgorithm)
//                        .compact();
//    }
//
//    // 토큰 검증
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (SecurityException | MalformedJwtException e) {
//            log.info("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token, 만료된 JWT token 입니다.");
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
//        } catch (IllegalArgumentException e) {
//            log.info("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
//        }
//        return false;
//    }
//
//    // 토큰에서 사용자 정보 가져오기
//    public Claims getUserInfoFromToken(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//    }
//
//    public Claims validToken(HttpServletRequest request) {
//        String token = resolveToken(request);
//
//        if(token == null || !validateToken(token)) {
//            throw new IllegalArgumentException("Token Error");
//        }
//
//        return getUserInfoFromToken(token);
//    }
//}
