package com.example.Calendar.task.provider

import com.example.Calendar.task.provider.domain.Task
import com.example.Calendar.task.provider.domain.Category
import com.example.Calendar.task.provider.domain.TaskProviderFacade
import spock.lang.Specification
import com.example.Calendar.task.provider.exception.InvalidDataException

class TaskSpec extends Specification implements CalendarSample{
    TaskProviderFacade taskProviderFacade = Mock(TaskProviderFacade.class)
//1
    def "Should create category with name"() {
        when: "Category with name 'school' is created"
            Category schoolCategory = new Category("school")
        then: "Category exist"
            equalsCategory(schoolCategory, schoolCategorySample)
    }

//2
    def "Should create task without category"() {
        when: "Creating new task"
            Task task = new Task("biologia")
        then: "Task should exist"
            equalsTask(task, taskWithoutCategorySample)
    }

//3
    def "Should create task with category"() {
        given: "There is 'school' category"
            Category schoolCategory = new Category("school")
        when: " Creating new task with schoolCategory"
            Task task = new Task("biologia", schoolCategory)
        then: "Task with category should exist"
            equalsTask(task, taskWithCategorySample)
    }

//4
    def "Should edit task category"() {
        given: " There are 'school' and 'home' categories"
            Category schoolCategory = new Category("school")
            Category homeCategory = new Category("home")
        and: "There is task with 'school' category"
            Task task = new Task("biologia", schoolCategory)
        when: "Task category being changed to 'home'"
            Task editedTask = taskProviderFacade.editTaskCategory(task, homeCategory)
        then: "Task data should be actualised"
            equalsCategory(editedTask.getCategory(), homeCategory)
    }

//5
    def "Should edit task name"() {
        given: "There is task with name 'biologia'"
            Task task = new Task("biologia")
        when: "Task name being changed to 'matematyka'"
            Task editedTask = taskProviderFacade.editTaskName(task, "matematyka")
        then: "Task data should be actualised"
            equalsTaskName(editedTask.getName(), "matematyka")
    }

//6
    def "Should not edit task name if its too long"() {
        given: "There is task with name 'biologia'"
            Task task = new Task("biologia")
        when: "Task name being changed to too long name"
            taskProviderFacade.editTaskName(task, toLongTaskNameSample)
        then: "Should throw error"
            thrown(InvalidDataException)
    }

//7
    def
    "Should delete existing task"() {
        given: "There is task"
            Task task = new Task("biologia")
        when: "Task being deleted"
            taskProviderFacade.deleteTask(task)
        then: "Task data should not exist"
            equals(taskProviderFacade.taskExistsInSystem(task), false)
    }

//8
    def "Should not add task with too long name"() {
        when: "Trying to add task with too long name"
            Task task = new Task(toLongTaskNameSample)
        then: "Should throw error"
            thrown(InvalidDataException)
    }

//9
    def "Should not create category with too long name"() {
        when: "Category is created with too long name"
            Category schoolCategory = new Category(tooLongCategoryNameSample)
        then: "Should throw error"
            thrown(InvalidDataException)
    }

//10
    def "Should not create category without name"() {
        when: "Category is created"
            Category schoolCategory = new Category()
        then: "Should throw error"
            thrown(InvalidDataException)
    }


//11
    def "Should edit category name"() {
        given: "There is category named 'home'"
            Category schoolCategory = new Category('home')
        when: "Category name being changed to 'office'"
            Category newCategoryName = taskProviderFacade.editCategoryName(schoolCategory, "office")
        then: "Task data should be actualised"
            equalsCategoryName(newCategoryName.getName(), "office" )
    }
}
