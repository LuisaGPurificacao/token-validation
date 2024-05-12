package br.com.jwt.validation.app.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.jwt.validation.fixture.AuthenticationFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationResourceITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidateTokenSuccess() throws Exception {
        mockMvc.perform(post("/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getValidRequest())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isTokenValid").value(true))
                .andExpect(jsonPath("$.errorDescription").doesNotExist());
    }

    @Test
    void testValidateTokenFail() throws Exception {
        mockMvc.perform(post("/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getInvalidPayloadRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.isTokenValid").value(false))
                .andExpect(jsonPath("$.errorDescription").value("JWT payload is invalid"));
    }

}