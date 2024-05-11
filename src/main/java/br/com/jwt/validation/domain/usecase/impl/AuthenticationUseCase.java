package br.com.jwt.validation.domain.usecase.impl;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.usecase.IAuthenticationUseCase;
import br.com.jwt.validation.domain.validation.ValidationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AuthenticationUseCase implements IAuthenticationUseCase {

    private final List<ValidationStrategy> validationStrategies;

    public AuthenticationUseCase(List<ValidationStrategy> validationStrategies) {
        this.validationStrategies = validationStrategies;
    }

    public AuthenticationResponse validateToken(UserEntity userEntity) {
        log.info("[USE CASE] Validating token information");
        boolean isTokenValid = validationStrategies.stream()
                .allMatch(strategy -> strategy.isValid(userEntity));

        log.info("[USE CASE] Token information validated");

        String errorDescription = null;
        if (!isTokenValid) {
            errorDescription = validationStrategies.stream()
                    .filter(strategy -> !strategy.isValid(userEntity))
                    .map(ValidationStrategy::getErrorMessage)
                    .collect(Collectors.joining(", "));
        }

        return AuthenticationResponse.builder()
                .isTokenValid(isTokenValid)
                .errorDescription(errorDescription)
                .build();
    }

}
