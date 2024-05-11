package br.com.jwt.validation.app.resource;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.service.IAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.jwt.validation.fixture.AuthenticationFixture.getValidRequest;
import static br.com.jwt.validation.fixture.AuthenticationFixture.getValidResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthenticationService service;

    @Test
    void testValidateTokenSuccess() throws Exception {
        when(service.validateToken(any(AuthenticationRequest.class))).thenReturn(getValidResponse());

        mockMvc.perform(post("/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getValidRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isTokenValid").value(true))
                .andExpect(jsonPath("$.errorDescription").doesNotExist());

        verify(service, times(1)).validateToken(any(AuthenticationRequest.class));
    }

}