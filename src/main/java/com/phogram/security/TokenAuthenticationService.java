package com.phogram.security;

import com.phogram.dto.UserDTO;
import com.phogram.utils.PGConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
/**
 * Created by gavin on 2017. 5. 7..
 * token 값을 header 에 넣을 경우 사용한다.
 */
@Component
public class TokenAuthenticationService {
    private final JwtTokenHandler jwtTokenHandler;
    @Autowired
    public TokenAuthenticationService(JwtTokenHandler jwtTokenHandler) {
        this.jwtTokenHandler = jwtTokenHandler;
    }

    public void addJwtTokenToHeader(HttpServletResponse response, UserDTO userDTO) {
        response.setHeader(PGConstants.CUSTOMHEADER, jwtTokenHandler.createTokenForUser(userDTO));
    }

    public Optional<UserDTO> generateAuthenticationFromRequest(HttpServletRequest request) throws Exception{
        final String token = request.getHeader(PGConstants.CUSTOMHEADER);
        if (token == null || token.isEmpty()) return null;
        return jwtTokenHandler.parseUserFromToken(token);
    }
}
