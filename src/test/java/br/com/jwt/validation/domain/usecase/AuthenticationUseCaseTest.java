package br.com.jwt.validation.domain.usecase;

import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.jwt.validation.fixture.AuthenticationFixture.getUserEntity;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthenticationUseCaseTest {

    @Autowired
    IAuthenticationUseCase useCase;

    @Test
    void testValidateTokenSuccess() {
        AuthenticationResponse response = useCase.validateToken(getUserEntity());
        assertTrue(response.getIsTokenValid());
        assertNull(response.getErrorDescription());
    }

    @Test
    void testValidateTokenErrorSeedInvalid() {
        UserEntity user = getUserEntity();
        user.setSeed(400);
        AuthenticationResponse response = useCase.validateToken(user);
        assertFalse(response.getIsTokenValid());
        assertEquals("Seed is not a prime number", response.getErrorDescription());
    }

    @Test
    void testValidateTokenErrorNameWithNumber() {
        UserEntity user = getUserEntity();
        user.setName("M4ria Oliveira");
        AuthenticationResponse response = useCase.validateToken(user);
        assertFalse(response.getIsTokenValid());
        assertEquals("Name is not only letters", response.getErrorDescription());
    }


    @Test
    void testValidateTokenErrorNameBiggerThan256Characters() {
        UserEntity user = getUserEntity();
        user.setName("Maria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria Oliveiraa" +
                "Maria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria OliveiraaMaria Oliveiraaaa");
        AuthenticationResponse response = useCase.validateToken(user);
        assertFalse(response.getIsTokenValid());
        assertEquals("Name contains more than 256 characters", response.getErrorDescription());
    }

}