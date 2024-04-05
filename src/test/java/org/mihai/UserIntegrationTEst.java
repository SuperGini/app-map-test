package org.mihai;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@Testcontainers
public class UserIntegrationTEst {

    @Autowired
    MockMvc mockMvc;

    @Container
    @ServiceConnection
    public static MySQLContainer mysql = new MySQLContainer(DockerImageName.parse("mysql:latest"));
//            .withDatabaseName("testdb")
//            .withUsername("test")
//            .withPassword("test");

//    @BeforeEach
//    void setUp() {
//        System.setProperty("spring.datasource.url", mysql.getJdbcUrl());
//        System.setProperty("spring.datasource.username", mysql.getUsername());
//        System.setProperty("spring.datasource.password", mysql.getPassword());
//    }


    @Test
    void testSaveUser() throws Exception {


        String userJson = "{\"username\": \"testUser\",\"age\": 25}";
        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.age").value(25));

    }
}
