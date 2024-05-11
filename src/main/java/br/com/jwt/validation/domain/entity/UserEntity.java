package br.com.jwt.validation.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntity {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Role")
    private UserRole role;
    @JsonProperty("Seed")
    private Integer seed;

}
