package br.com.jwt.validation.fixture;

import br.com.jwt.validation.app.dto.request.AuthenticationRequest;
import br.com.jwt.validation.app.dto.response.AuthenticationResponse;
import br.com.jwt.validation.domain.entity.UserEntity;
import br.com.jwt.validation.domain.entity.UserRole;

public class AuthenticationFixture {

    public static AuthenticationRequest getValidRequest() {
        return new AuthenticationRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.8qBykiT3AAOZqS0ov2wYmuIMJZwDiEGiFiJ_Pvfl6-Y");
    }

    public static AuthenticationRequest getInvalidTokenRequest() {
        return new AuthenticationRequest("eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg");
    }

    public static AuthenticationRequest getInvalidPayloadRequest() {
        return new AuthenticationRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.pWKqHSiSZau7ORXrX70vgOkDiXptsyd6Lahxd9esW5E");
    }

    public static AuthenticationRequest getInvalidRoleRequest() {
        return new AuthenticationRequest("eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQ2xpZW50IiwiU2VlZCI6Ijc4NDEiLCJOYW1lIjoiVG9uaW5obyBBcmF1am8ifQ.SMpV_So4WKWYmfSKMkwleqqyE2GKDRvfSHVh1UP-QPE");
    }

    public static AuthenticationResponse getValidResponse() {
        return AuthenticationResponse.builder()
                .isTokenValid(true)
                .build();
    }

    public static UserEntity getUserEntity() {
        return UserEntity.builder()
                .name("Maria Oliveira")
                .role(UserRole.ADMIN)
                .seed(5501)
                .build();
    }

}
