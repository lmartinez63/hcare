package com.landl.hcare.config;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        int internalErrorCode = 99;
        if(authException instanceof BadCredentialsException){
            internalErrorCode = 1;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, String.valueOf(internalErrorCode));
    }
}