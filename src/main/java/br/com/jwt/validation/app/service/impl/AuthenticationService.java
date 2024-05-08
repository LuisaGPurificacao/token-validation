package br.com.jwt.validation.app.service.impl;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.app.service.IAuthenticationService;
import br.com.jwt.validation.domain.entity.UserEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.MacSigner;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AuthenticationService implements IAuthenticationService {

    private final String secretKey;

    public AuthenticationService(@Value("${jwt.secret}") String secret) {
        this.secretKey = secret;
    }

    public AuthenticationResponse validateToken(AuthenticationRequest request) {
        log.info("[SERVICE] Decoding token");
        String json = decodeJwt(request.getToken());
        UserEntity user = getUser(json);
        if (Objects.isNull(user))
            return new AuthenticationResponse(false);
        log.info("[SERVICE] Token decoded");
        log.debug("[SERVICE] User: {}", user.toString());
        return new AuthenticationResponse(true);
    }

    private UserEntity getUser(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, UserEntity.class);
        } catch (Exception ex) {
            log.error("Error while converting JSON to User: {}", ex.getMessage());
            return null;
        }
    }

    private String decodeJwt(String jwt) {
        SignatureVerifier verifier = new MacSigner(secretKey);
        return JwtHelper.decodeAndVerify(jwt, verifier).getClaims();
    }

}
