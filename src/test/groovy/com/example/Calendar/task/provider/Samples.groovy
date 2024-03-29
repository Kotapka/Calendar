package com.example.inz

import com.example.inz.category.domain.Category
import com.example.inz.category.dto.CategoryDto
import com.example.inz.customer.operation.domain.Customer
import com.example.inz.customer.operation.dto.CustomerDto
import com.example.inz.task.provider.domain.Task
import com.example.inz.task.provider.dto.AssignedTaskDto
import com.example.inz.task.provider.dto.TaskDto

trait Samples {
    Customer USER_EXAMPLE = Customer.builder().id(1).name("Jan").login("JAN").surname("Kowalski").password("password").build()
    Category CATEGORY_EXAMPLE = Category.builder().id(1).name("school").user(USER_EXAMPLE).build()
    Task TASK_EXAMPLE = Task.builder().id(1).name("math").category(CATEGORY_EXAMPLE).user(USER_EXAMPLE).build()
    CategoryDto CATEGORY_DTO_EXAMPLE = CategoryDto.builder().name("school").user("JAN").build()
    TaskDto TASK_DTO_EXAMPLE = TaskDto.builder().user("JAN").category("school").name("math").build()

    private Map<String, Object> DEFAULT_USER_DATA = [
            id: 1L,
            name: '',
            login: ''
    ] as Map<String, Object>

    private Map<String, Object> DEFAULT_CATEGORY_DATA = [
            name: '',
            user: ''
    ] as Map<String, Object>

    private Map<String, Object> DEFAULT_TASK_DATA = [
            name: '',
            category: '',
            user: ''
    ] as Map<String, Object>

    void equalsCategory(Category category1, Category category2){
        assert category1.name == category2.name
        assert category1.user == category2.user
    }

    CustomerDto createCustomer(Map<String, Object> changes = [:]) {
        def changesWithDefaults = DEFAULT_USER_DATA + changes
        CustomerDto.builder()
                .id(changesWithDefaults.userId as Long)
                .name(changesWithDefaults.username as String)
                .login(changesWithDefaults.login as String)
                .build()
    }

    CategoryDto createCategory(Map<String,Object> changes = [:]){
        def changesWithDefaults = DEFAULT_CATEGORY_DATA + changes
        CategoryDto.builder()
            .name(changesWithDefaults.name as String)
            .user(changesWithDefaults.user as String)
            .build()
    }

    TaskDto createTask(Map<String,Object> changes = [:]){
        def changesWithDefaults = DEFAULT_TASK_DATA + changes
        TaskDto.builder()
                .name(changesWithDefaults.name as String)
                .category(changesWithDefaults.category as String)
                .user(changesWithDefaults.user as String)
                .build()
    }

    void equalsCustomers(CustomerDto result, CustomerDto expected) {
        assert result.id == expected.id
        assert result.name == expected.name
        assert result.login == expected.login
    }

    void equalsCategoryDto(CategoryDto category1, CategoryDto category2){
        assert category1.name == category2.name
        assert category1.user == category2.user
    }

    void equalsTasks(TaskDto task1, TaskDto task2){
        assert task1.name == task2.name
        assert task1.user == task2.user
    }

    void equalsAssignedTask(AssignedTaskDto task1, AssignedTaskDto task2){
        assert task1.category == task2.category
        assert task1.user == task2.user
        assert task1.task == task2.task
    }
}