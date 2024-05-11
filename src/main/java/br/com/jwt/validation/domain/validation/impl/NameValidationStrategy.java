package br.com.jwt.validation.domain.validation.impl;

import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.validation.ValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class NameValidationStrategy implements ValidationStrategy {

    private String errorDescription;

    public boolean isValid(UserEntity userEntity) {
        String name = userEntity.getName();
        boolean isNameOnlyLetters = name.matches("^[a-zA-Z\\s]*$");
        if (!isNameOnlyLetters) {
            errorDescription = "Name is not only letters";
            return false;
        }
        boolean isNameWithinMaxLength = name.length() <= 256;
        if (!isNameWithinMaxLength) {
            errorDescription = "Name contains more than 256 characters";
            return false;
        }
        return true;
    }

    public String getErrorMessage() {
        return errorDescription;
    }

}