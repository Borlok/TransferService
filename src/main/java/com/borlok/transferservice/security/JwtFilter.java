package com.borlok.transferservice.security;

import com.borlok.transferservice.exception.auth.AuthenticationException;
import com.borlok.transferservice.exception.auth.AuthenticationExceptionMessage;
import com.borlok.transferservice.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Erofeevskiy Yuriy
 */
@Slf4j
public class JwtFilter extends GenericFilterBean {
    private final TokenService tokenService;

    public JwtFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.debug("JWT Filter");
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader("Authorization");
        if (token != null) {
                Authentication auth = tokenService.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

        }
        chain.doFilter(req, res);
    }
}
