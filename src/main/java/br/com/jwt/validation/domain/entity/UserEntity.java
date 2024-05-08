package br.com.jwt.validation.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @JsonProperty("Name")
    private String name;
    @JsonProperty("Role")
    private UserRole role;
    @JsonProperty("Seed")
    private Integer seed;

}
