package com.example.Calendar.task.provider

import com.example.Calendar.PostgresContainerConfig
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner.class)
@WithMockUser
@Testcontainers
abstract class IntegrationSpec extends Specification {
    static {
        PostgresContainerConfig.init()
        System.setProperty("spring.datasource.url", PostgresContainerConfig.getPostgreSQLContainer().getJdbcUrl())
        System.setProperty("spring.datasource.username", PostgresContainerConfig.getPostgreSQLContainer().getUsername())
        System.setProperty("spring.datasource.password", PostgresContainerConfig.getPostgreSQLContainer().getPassword())
    }

    @Autowired
    protected WebApplicationContext context

    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    CalendarApi api

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build()
        api = new CalendarApi(mockMvc, objectMapper)
    }
}