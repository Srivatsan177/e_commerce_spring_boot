package com.srivatsan177.e_commerce.configurations;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mysql.cj.log.Slf4JLogger;
import com.srivatsan177.e_commerce.utils.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class SecurityFilter extends GenericFilterBean {

    private static Logger logger = LoggerFactory.getLogger(Slf4JLogger.class);
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String jwt = httpRequest.getHeader("Authorization");
        try {
            if(jwt == null) {
                jwt = "";
            }
            String username = jwtUtil.getUserClaim(jwt);
            UserDetails userDetailsService = userDetailsServiceImpl.loadUserByUsername(username);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, "somepass", userDetailsService.getAuthorities()));
        } catch (UsernameNotFoundException e) {
            logger.debug(e.getMessage());
            System.out.println(e.getMessage());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(" ", " ", new ArrayList<>() {
                {
                    add(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
                }
            }));
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
