package br.com.jwt.validation.app.service;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.dto.response.AuthenticationResponse;

public interface IAuthenticationService {

    AuthenticationResponse validateToken(AuthenticationRequest request);

}
