package br.com.jwt.validation.app.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private boolean isTokenValid;
    private String errorDescription;

    public boolean getIsTokenValid() {
        return isTokenValid;
    }

}
