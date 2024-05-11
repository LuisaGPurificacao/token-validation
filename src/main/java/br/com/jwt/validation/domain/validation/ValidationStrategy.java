package br.com.jwt.validation.domain.validation;

import br.com.jwt.validation.domain.entity.UserEntity;

public interface ValidationStrategy {

    boolean isValid(UserEntity userEntity);

    String getErrorMessage();

}
