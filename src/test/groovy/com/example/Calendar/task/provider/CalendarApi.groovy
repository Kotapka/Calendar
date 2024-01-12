package com.example.Calendar.task.provider

import com.example.inz.category.dto.CategoryDto
import com.example.inz.customer.operation.dto.CustomerDto
import com.example.inz.customer.operation.dto.LoginDto
import com.example.inz.customer.operation.dto.SignUpDto
import com.example.inz.customer.operation.dto.UserLoginDto
import com.example.inz.task.provider.dto.AssignedTaskDto
import com.example.inz.task.provider.dto.EditedTaskDto
import com.example.inz.task.provider.dto.TaskDto
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

        CustomerDto login(LoginDto loginUser) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(loginUser))
            )
            checkResponse(perform.andReturn().response)
            CustomerDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(LoginDto.class))

            value
        }

        CategoryDto addCategory(CategoryDto categoryDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/addCategory")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(categoryDto))
            )
            checkResponse(perform.andReturn().response)
            CategoryDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(CategoryDto.class))

            value
        }

        CategoryDto getCategories(CategoryDto categoryDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/addCategory")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(categoryDto))
            )
            checkResponse(perform.andReturn().response)
            CategoryDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(CategoryDto.class))

            value
        }

        TaskDto addTask(TaskDto taskDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/addTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(taskDto))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(TaskDto.class))

            value
        }

        TaskDto getTask(UserLoginDto userLoginDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/user/getTasks")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userLoginDto))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(UserLoginDto.class))

            value
        }

        AssignedTaskDto saveAssignedTask(AssignedTaskDto assignedTaskDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/saveAssignedTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(assignedTaskDto))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(AssignedTaskDto.class))

            value
        }

        AssignedTaskDto getAssignedTask(UserLoginDto userLoginDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/user/getAssignedTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(userLoginDto))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(AssignedTaskDto.class))

            value
        }

        AssignedTaskDto deleteAssignedTask(Long taskId) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/deleteAssignedTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(taskId))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(AssignedTaskDto.class))

            value
        }

        EditedTaskDto editAssignedTask(EditedTaskDto editedTaskDto) {
            ResultActions perform = mvc.perform(MockMvcRequestBuilders.post("/api/editAssignedTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(editedTaskDto))
            )
            checkResponse(perform.andReturn().response)
            TaskDto value = mapper.readValue(perform.andReturn().response.getContentAsString(StandardCharsets.UTF_8), mapper.getTypeFactory().constructType(EditedTaskDto.class))

            value
        }




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
