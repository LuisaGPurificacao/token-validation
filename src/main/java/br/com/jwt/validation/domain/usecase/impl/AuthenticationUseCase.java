package br.com.jwt.validation.domain.usecase.impl;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.usecase.IAuthenticationUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthenticationUseCase implements IAuthenticationUseCase {

    private String errorDescription = null;

    public AuthenticationResponse validateToken(UserEntity userEntity) {
        log.info("[USE CASE] Validating token information");
        boolean isSeedValid = validateSeed(userEntity.getSeed());

        boolean isNameValid = validateName(userEntity.getName());
        log.info("[USE CASE] Token information validated");

        boolean isTokenValid = isSeedValid && isNameValid;

        if (isTokenValid)
            errorDescription = null;

        return AuthenticationResponse.builder()
                .isTokenValid(isTokenValid)
                .errorDescription(errorDescription)
                .build();
    }

    private boolean validateSeed(Integer seed) {
        for (int i = 2; i <= Math.sqrt(seed); i++) {
            if (seed % i == 0) {
                errorDescription = "Seed is not a prime number";
                return false;
            }
        }
        return true;
    }

    private boolean validateName(String name) {
        boolean isNameOnlyLetters = name.matches("^[a-zA-Z\\s]*$");
        if (!isNameOnlyLetters)
            errorDescription = "Name is not only letters";
        boolean isNameWithinMaxLength = name.length() <= 256;
        if (!isNameWithinMaxLength)
            errorDescription = "Name contains more than 256 characters";
        return (isNameOnlyLetters && isNameWithinMaxLength);
    }

}
