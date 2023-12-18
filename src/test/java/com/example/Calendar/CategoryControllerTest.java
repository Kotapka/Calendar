package com.example.Calendar;

import com.example.inz.category.dto.CategoryDto;
import com.example.inz.customer.operation.dto.UserLoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldAddCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto(); // Utwórz odpowiedni obiekt DTO z danymi testowymi

        mockMvc.perform(post("/api/addCategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"TestCategory\", \"description\": \"TestDescription\" }")) // Przykładowe dane JSON
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("TestCategory"))
                .andExpect(jsonPath("$.description").value("TestDescription"));
    }

    @Test
    public void shouldGetCategoriesByUser() throws Exception {
        UserLoginDto userDto = new UserLoginDto(); // Utwórz odpowiedni obiekt DTO z danymi testowymi

        mockMvc.perform(post("/api/user/getCategories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"username\": \"TestUser\", \"password\": \"TestPassword\" }")) // Przykładowe dane JSON
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").isNotEmpty())
                .andExpect(jsonPath("$[0].description").isNotEmpty());
    }
}
