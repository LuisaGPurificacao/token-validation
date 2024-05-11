package br.com.jwt.validation.app.service;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.usecase.IAuthenticationUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.jwt.validation.fixture.AuthenticationFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class AuthenticationServiceTest {

    @Autowired
    IAuthenticationService service;

    @Mock
    IAuthenticationUseCase useCase;

    @Test
    void testValidateTokenSuccess() {
        when(useCase.validateToken(any())).thenReturn(getValidResponse());
        AuthenticationResponse response = service.validateToken(getValidRequest());
        assertTrue(response.getIsTokenValid());
        assertNull(response.getErrorDescription());
    }

    @Test
    void testValidateTokenErrorInvalidToken() {
        AuthenticationResponse response = service.validateToken(getInvalidTokenRequest());
        assertFalse(response.getIsTokenValid());
        assertEquals("JWT is invalid", response.getErrorDescription());
    }

    @Test
    void testValidateTokenErrorInvalidPayload() {
        AuthenticationResponse response = service.validateToken(getInvalidPayloadRequest());
        assertFalse(response.getIsTokenValid());
        assertEquals("JWT payload is invalid", response.getErrorDescription());
    }

    @Test
    void testValidateTokenErrorInvalidRole() {
        AuthenticationResponse response = service.validateToken(getInvalidRoleRequest());
        assertFalse(response.getIsTokenValid());
        assertEquals("JWT payload is invalid", response.getErrorDescription());
    }

}