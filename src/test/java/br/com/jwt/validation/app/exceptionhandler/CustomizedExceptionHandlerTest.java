package br.com.jwt.validation.app.exceptionhandler;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomizedExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthenticationService service;

    @Test
    void testExceptionHandler() throws Exception {
        when(service.validateToken(any(AuthenticationRequest.class))).thenThrow(new RuntimeException("erro"));

        mockMvc.perform(post("/validate-token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(getValidRequest())))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.detail").value("erro"));
    }


}