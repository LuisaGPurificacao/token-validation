package br.com.jwt.validation.app.service.impl;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.app.service.IAuthenticationService;
import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.usecase.IAuthenticationUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IAuthenticationUseCase useCase;

    public AuthenticationService(@Value("${jwt.secret}") String secret) {
        this.secretKey = secret;
    }

    public AuthenticationResponse validateToken(AuthenticationRequest request) {
        log.info("[SERVICE] Decoding token");
        String claims = decodeJwt(request.getToken());
        if (Objects.isNull(claims))
            return new AuthenticationResponse(false, "JWT is invalid");

        UserEntity user = getUser(claims);
        if (Objects.isNull(user))
            return new AuthenticationResponse(false, "JWT payload is invalid");
        log.info("[SERVICE] Token decoded");
        log.debug("[SERVICE] User: {}", user.toString());

        return useCase.validateToken(user);
    }

    private UserEntity getUser(String claims) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(claims, UserEntity.class);
        } catch (Exception ex) {
            log.error("Error while converting JSON to User: {}", ex.getMessage());
            return null;
        }
    }

    private String decodeJwt(String jwt) {
        try {
            SignatureVerifier verifier = new MacSigner(secretKey);
            String claims = JwtHelper.decodeAndVerify(jwt, verifier).getClaims();
            return claims;
        } catch (Exception ex) {
            log.error("Error while decoding JWT: {}", ex.getMessage());
            return null;
        }
    }

}
