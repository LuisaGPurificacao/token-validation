package br.com.jwt.validation.domain.usecase.impl;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.usecase.IAuthenticationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationUseCase implements IAuthenticationUseCase {

    private String description = null;

    public AuthenticationResponse validateToken(UserEntity userEntity) {
        log.info("[USE CASE] Validating token information");
        boolean isNameValid = validateName(userEntity.getName());

        boolean isSeedValid = validateSeed(userEntity.getSeed());
        log.info("[USE CASE] Token information validated");

        boolean isTokenValid = isSeedValid && isNameValid;

        return new AuthenticationResponse(isTokenValid, description);
    }

    private boolean validateSeed(Integer seed) {
        for (int i = 2; i <= Math.sqrt(seed); i++) {
            if (seed % i == 0) {
                description = "Seed is not a prime number";
                return false;
            }
        }
        return true;
    }

    private boolean validateName(String name) {
        boolean isNameOnlyLetters = name.matches("^[a-zA-Z\\s]*$");
        if (!isNameOnlyLetters)
            description = "Seed is not a prime number";
        boolean isNameWithinMaxLength = name.length() <= 256;
        if (!isNameWithinMaxLength)
            description = "Name contains more than 256 characters";
        return (isNameOnlyLetters && isNameWithinMaxLength);
    }

}
