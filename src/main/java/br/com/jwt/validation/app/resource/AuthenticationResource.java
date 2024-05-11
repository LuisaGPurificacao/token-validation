package br.com.jwt.validation.app.resource;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.app.service.IAuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthenticationResource {

    private final IAuthenticationService service;

    public AuthenticationResource(IAuthenticationService service) {
        this.service = service;
    }

    @PostMapping(path = "/validate-token")
    public AuthenticationResponse validateToken(@RequestBody AuthenticationRequest request) {
        log.info("[RESOURCE] Received JWT, starting process");
        log.debug("[RESOURCE] Token: {}", request.getToken());
        AuthenticationResponse response = service.validateToken(request);
        log.info("[RESOURCE] Process finished, returning if token is valid");
        return response;
    }

}
