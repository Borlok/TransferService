package com.borlok.transferservice.security;

import com.borlok.transferservice.exception.auth.AuthenticationException;
import com.borlok.transferservice.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String token = request.getHeader("Authorization");
        try {
            if (token != null && tokenService.isValid(token)) {
                Authentication auth = tokenService.authenticate(token);
                if (auth != null)
                    SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (AuthenticationException e) {
            log.error(e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            throw e;
        }
        chain.doFilter(req, res);
    }
}
