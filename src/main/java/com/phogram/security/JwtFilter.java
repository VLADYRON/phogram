package com.phogram.security;

import com.phogram.utils.PGConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.catalina.servlet4preview.GenericFilter;

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
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader(PGConstants.AUTHORIZATION);

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            filterChain.doFilter(servletRequest, servletResponse);
        } else {

            if (authHeader == null || !authHeader.startsWith(PGConstants.CUSTOMHEADER)) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(8);

            try {
                final Claims claims = Jwts.parser().setSigningKey(PGConstants.SECRETKEY).parseClaimsJws(token).getBody();
                request.setAttribute(PGConstants.CLAIMS, claims);
            } catch (final SignatureException e) {

                throw new ServletException("Invalid token.");
            }

            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
