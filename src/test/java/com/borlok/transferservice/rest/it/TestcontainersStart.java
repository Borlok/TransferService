package com.borlok.transferservice.rest.it;

import com.borlok.transferservice.Utils;
import com.borlok.transferservice.rest.AuthenticationRestControllerV1;
import com.borlok.transferservice.rest.it.model.TestAuthData;
import com.borlok.transferservice.service.impl.AuthenticationServiceImpl;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Erofeevskiy Yuriy
 */

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestcontainersStart {
    @Autowired
    private AuthenticationServiceImpl authenticationService;
    private static PostgreSQLContainer<?> postgresContainer;

    static {
        postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
        postgresContainer.start();
    }
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.flyway.url", postgresContainer::getJdbcUrl);
        registry.add("spring.flyway.user", postgresContainer::getUsername);
        registry.add("spring.flyway.password", postgresContainer::getPassword);
    }


    protected String getToken() {
        try {
            MockMvc tokenClient = MockMvcBuilders
                    .standaloneSetup(new AuthenticationRestControllerV1(authenticationService))
                    .build();
            TestAuthData test = Utils.TestAuth("Petr@test.ts", "test");
            return tokenClient.perform(post("/auth")
                            .contentType("application/json")
                            .accept(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(test)))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
