package com.phogram.security;

import com.phogram.utils.PGConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.catalina.servlet4preview.GenericFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gavin on 2017. 5. 5..
 */
public class JwtFilter extends GenericFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader(PGConstants.AUTHORIZATION);
        /*웹 브라우저에서 최초에 한번 테스트 커넥션을 요청할때*/
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (authHeader == null || !authHeader.startsWith(PGConstants.CUSTOMHEADER)) {
                log.error("Missing or invalid Authorization header");
                throw new ServletException("Missing or invalid Authorization header");
            }
            final String token = authHeader.substring(8);
            try {
                final Claims claims = Jwts.parser().setSigningKey(PGConstants.SECRETKEY).parseClaimsJws(token).getBody();
                request.setAttribute(PGConstants.CLAIMS, claims);
            } catch (final SignatureException e) {
                log.error(e.getMessage());
                throw new ServletException("Invalid token.");
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
