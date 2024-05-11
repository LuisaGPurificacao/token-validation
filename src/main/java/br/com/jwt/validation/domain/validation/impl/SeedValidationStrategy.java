package br.com.jwt.validation.domain.validation.impl;

import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.validation.ValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class SeedValidationStrategy implements ValidationStrategy {

    private String errorDescription;

    public boolean isValid(UserEntity userEntity) {
        int seed = userEntity.getSeed();
        for (int i = 2; i <= Math.sqrt(seed); i++) {
            if (seed % i == 0) {
                errorDescription = "Seed is not a prime number";
                return false;
            }
        }
        return true;
    }

    public String getErrorMessage() {
        return errorDescription;
    }

}
