package com.borlok.transferservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * @author Erofeevskiy Yuriy
 */

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthenticationRequest(
        String username,
        @JsonProperty(access = WRITE_ONLY) String password
){}
