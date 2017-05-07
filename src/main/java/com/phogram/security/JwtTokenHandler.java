package com.phogram.security;


import com.phogram.dto.UserDTO;
import com.phogram.service.UserService;
import com.phogram.utils.PGConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
/**
 * Created by gavin on 2017. 5. 7..
 */
@Component
public class JwtTokenHandler {
    @Autowired
    private UserService userService;

    /* 토큰값으로 유저 정보 파싱 */
    Optional<UserDTO> parseUserFromToken(String token) throws Exception{
        String username = Jwts.parser()
                .setSigningKey(PGConstants.SECRETKEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.findByUsername(username);
    }
    /*토큰 생성*/
    public String createTokenForUser(UserDTO user) {
        //NOTE:security active time 설정(토큰 유효 시간 1일 설정)
        final ZonedDateTime afterOneWeek = ZonedDateTime.now().plusDays(1);
        return Jwts.builder()
                .setSubject(user.getUsername()).claim(PGConstants.ROLES,PGConstants.USER) // role,user 를 추가.
                .signWith(SignatureAlgorithm.HS512, PGConstants.SECRETKEY)
                .setExpiration(Date.from(afterOneWeek.toInstant()))
                .compact();
    }
    /* request 정보로 사용자 정보 추출 */
    public Optional<UserDTO> getUserDTO(HttpServletRequest request) throws Exception {
        final String authHeader = request.getHeader(PGConstants.AUTHORIZATION);
        final String token = authHeader.substring(8);
        return parseUserFromToken(token);
    }
}
