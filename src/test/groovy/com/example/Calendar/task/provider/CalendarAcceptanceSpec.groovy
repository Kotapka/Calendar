package com.example.Calendar.task.provider

import com.example.inz.Samples
import com.example.inz.category.dto.CategoryDto
import com.example.inz.customer.operation.dto.LoginDto
import com.example.inz.customer.operation.dto.SignUpDto
import com.example.inz.customer.operation.dto.UserLoginDto
import com.example.inz.task.provider.dto.AssignedTaskDto
import com.example.inz.task.provider.dto.TaskDto

class CalendarAcceptanceSpec extends IntegrationSpec implements Samples{

    def "Should create new user"() {
        when: "creates new user"
        def result = api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        then: "user is created"
        equalsCustomers(result, createCustomer(userId: result.id, username: "Jan", login: "KowalskiJan"))
    }

    def "Should login user"() {
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        when: "user try to login"
        def result = api.customer().login(new LoginDto("KowalskiJan","Password"))
        then: "user is being logged"
        equalsCustomers(result, createCustomer(userId: result.id, username: "Jan", login: "KowalskiJan"))
    }

    def "Should add category"() {
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        when: "user try to add category"
        def result = api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        then: "category exist"
        equalsCategoryDto(result, createCategory(name:"CategoryTest", user:"KowalskiJan"))
    }

    def "Should get categories"(){
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        and: "category already exist"
        def category = api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        when: "user try to get categories"
        def result = api.customer().getCategories(new CategoryDto("CategoryTest","KowalskiJan"))
        then: "category exist"
        equalsCategoryDto(result,category)
    }

    def "Should add task"() {
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        and: "category already exist"
        api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        when: "user try to add task"
        def result = api.customer().addTask(new TaskDto("TaskTest","CategoryTest","KowalskiJan"))
        then: "task exist"
        equalsTasks(result, createTask(name:"CategoryTest",category: "CategoryTest", user:"KowalskiJan"))
    }

    def "Should get tasks"(){
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        and: "category already exist"
        api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        and: "task already exist"
        def task = api.customer().addTask(new TaskDto("TaskTest","CategoryTest","KowalskiJan"))
        when: "user try to get tasks"
        def result = api.customer().getTask(new UserLoginDto("KowalskiJan"))
        then: "category exist"
        equalsTasks(result,task)
    }

    def "Should assign task"(){
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        and: "category already exist"
        api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        and: "task already exist"
        api.customer().addTask(new TaskDto("TaskTest","CategoryTest","KowalskiJan"))
        when: "user try to assign task"
        def result = api.customer().saveAssignedTask(new AssignedTaskDto(1,true,new Date(2023,2,2),new Date(2023,2,2),"descritpion","CategoryTest","KowalskiJan","TaskTest"))
        then: "assigned task exist"
        equalsAssignedTask(result,new AssignedTaskDto(1,true,new Date(2023,2,2),new Date(2023,2,2),"descritpion","CategoryTest","KowalskiJan","TaskTest"))
    }

    def "Should get assign task"(){
        given: "user already exist"
        api.customer().register(new SignUpDto("Jan","Kowalski","KowalskiJan","Password"))
        and: "category already exist"
        api.customer().addCategory(new CategoryDto("CategoryTest","KowalskiJan"))
        and: "task already exist"
        api.customer().addTask(new TaskDto("TaskTest","CategoryTest","KowalskiJan"))
        and: "assigned task already exist"
        api.customer().saveAssignedTask(new AssignedTaskDto(1,true,new Date(2023,2,2),new Date(2023,2,2),"descritpion","CategoryTest","KowalskiJan","TaskTest"))
        when: "user try to get assigned task"
        def result = api.customer().getAssignedTask(new UserLoginDto("1"))
        then: "assigned task exist"
        equalsAssignedTask(result,new AssignedTaskDto(1,true,new Date(2023,2,2),new Date(2023,2,2),"descritpion","CategoryTest","KowalskiJan","TaskTest"))
    }


    def cleanup() {
        api.customer().cleanup()
    }
}
