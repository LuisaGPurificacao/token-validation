package br.com.jwt.validation.domain.usecase;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;

public interface IAuthenticationUseCase {

    AuthenticationResponse validateToken(UserEntity userEntity);

}
