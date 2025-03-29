package com.borlok.transferservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @author Erofeevskiy Yuriy
 */
@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public final class AuthenticationRequest {
    private String username;
    @JsonProperty(access = WRITE_ONLY)
    private String password;

    public AuthenticationRequest(
            String username,
            @JsonProperty(access = WRITE_ONLY) String password
    ) {
        this.username = username;
        this.password = password;
    }
}
