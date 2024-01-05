package com.example.Calendar.task.provider

import com.example.inz.customer.operation.dto.CustomerDto
import com.example.inz.customer.operation.dto.SignUpDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import java.nio.charset.StandardCharsets

class CalendarApi {
    private final CustomerApi customerApi

    CalendarApi(MockMvc mockMvc, ObjectMapper objectMapper){
    this.customerApi = new CustomerApi(mockMvc,objectMapper)
    }

    CustomerApi customer(){
        customerApi
    }


    class CustomerApi {
        private final MockMvc mvc
        private final ObjectMapper mapper

        CustomerApi(MockMvc mvc, ObjectMapper mapper) {
            this.mvc = mvc
            this.mapper = mapper
        }

        CustomerDto register(SignUpDto createUser) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(createUser))
            )
            checkResponse(perform.andReturn().response)
            CustomerDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(SignUpDto.class))

            value
        }

//        UserDto getUserByUsername(String username) {
//            ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/user/{username}", username))
//            checkResponse(perform.andReturn().response)
//            UserDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(UserDto.class))
//            value
//        }

        void cleanup() {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.get("/api/user/cleanup"))
            checkResponse(perform.andReturn().response)
        }
    }

    private static void checkResponse(MockHttpServletResponse response) {
        if(response.status != 200) {
            throw new RuntimeException(response.getIncludedUrl() + " failed with status " + response.status)
        }
    }

}
