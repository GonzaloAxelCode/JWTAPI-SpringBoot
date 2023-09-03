package com.JWT.JsonWebToken.Jwt;

import com.JWT.JsonWebToken.Utils.Responses.ErrorResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtExceptionHelper {

    public void handleAuthenticationException(HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(new ErrorResponse("Unauthorized", "Usuario no autorizado, permisos insuficientes",new HashMap<>())));
    }

    public void handleExpiredJwtException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(new ErrorResponse("Unauthorized", "El token ha expirado",new HashMap<>())));
    }

    public void handleSignatureException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(new ErrorResponse("Unauthorized", "Firma del token no válida",new HashMap<>())));
    }

    public void handleJwtException(HttpServletResponse response, String errorMessage) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(convertObjectToJson(new ErrorResponse("Unauthorized", errorMessage,new HashMap<>())));
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
