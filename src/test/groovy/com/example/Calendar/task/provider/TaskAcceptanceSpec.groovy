package com.example.Calendar.task.provider

import com.example.Calendar.task.provider.domain.Category
import com.example.Calendar.task.provider.domain.Task
import com.example.Calendar.task.provider.domain.TaskProviderFacade
import spock.lang.Specification
import spock.lang.Unroll
import com.example.Calendar.task.provider.exception.InvalidDataException

class TaskAcceptanceSpec extends Specification implements CalendarSample{
    TaskProviderFacade taskProviderFacade = Mock(TaskProviderFacade.class)

//1
    def "Should assign task to specific day"() {
        given: "There is existing task"
            Task task = new Task(sampleTaskName,schoolCategorySample)
        when: "Assigning task to specific day"
            Task assignedTask = taskProviderFacade.assignTaskToDay(task, "29.09.2023")
        then: "Task should be assigned to specific day"
            equalsDays(taskProviderFacade.getTasksFromDay(assignedTask,"29.09.2023"),sampleDayWithTask)
    }

//2
    def "Should delete existing task from specific day"() {
        given: "There is task assigned to specific day in planner"
            Task assignedTask = taskProviderFacade.assignTaskToDay(taskWithCategorySample, "29.09.2023")
        when: "Task being unassigned"
            taskProviderFacade.unassigneTask(assignedTask, "29.09.2023")
        then: "Task should not be assigned to specific day"
            equals(taskProviderFacade.taskExistsInDay(assignedTask,"29.09.2023"),false)
    }

//3
    @Unroll
    def "Should edit start date and end date in existing task on a specific day if start date and end date are correct"() {
        given: "There is task assigned to specific day with start date set to '29.09.2023 12:00' and end date set to '29.09.2023 16:00'"
            Task task = taskProviderFacade.assignTaskToDay(createTaskSample(startDate: "29.09.2023 12:00", endDate: "29.09.2023 16:00"), "29.09.2023")
        when: "Task start date being setted to STARTDATE"
            Task editedAssignedTask = taskProviderFacade.editAssignedTaskDate(task,STARTDATE,ENDDATE)
        then: "Task data should be actualised"
            equalsTask(editedAssignedTask,createTaskSample(startDate: STARTDATE, endDate: ENDDATE), "29.09.2023")
        where:
        STARTDATE | ENDDATE
        '11:00'   | '12:00'
        '12:10'   | '16:12'
        '14:00'   | '15:00'
        '18:00'   | '18:15'
    }


//4
    @Unroll
    def "Should not edit start date and end date in existing task on a specific day if start date and end date are correct"() {
        given:"There is task assigned to specific day with start date set to '29.09 .2023 12: 00' and end date set to '29.09 .2023 16: 00'"
            Task task = taskProviderFacade.assignTaskToDay(createTaskSample(startDate: "29.09.2023 12:00", endDate: "29.09.2023 16:00"), "29.09.2023")
        when: "Task 'start date' being setted to $STARTDATE"
            taskProviderFacade.editAssignedTaskDate(task, STARTDATE, ENDDATE)
        then: "Error should be thrown"
            thrown(InvalidDataException)
        where:
        STARTDATE | ENDDATE
        '11:00'   | '09:00'
        '17:10'   | '16:12'
        '12:00'   | '11:59'
        '20:00'   | '18:15'
    }


//5
    def "Should not unassign task from specific day if task being deleted on side panel" ( ) {
        given: "There is task on side panel"
            Task task = new Task("Informatyka")
        and: "There is the same task assigned to specific day in planner"
            taskProviderFacade.assignTaskToDay(task(startDate: "29.09.2023 12:00", endDate: "29.09.2023 16:00"), "29.09.2023")
        when: "Task on side panel is deleted"
            taskProviderFacade.deleteTask(task)
        then: "Task in specific day should still be assigned"
            equals(taskProviderFacade.taskExistsInDay(task,"29.09.2023"), false )
    }


//6
    def "Should not assign task to specific day if data is wrong" ( ) {
        given: "There is task"
            Task task = new Task("Informatyka")
        when: "Trying to assign task to specific day with a $STARTDATE and $ENDDATE"
            taskProviderFacade.assignTaskToDay(task(startDate: STARTDATE, endDate: ENDDATE), "29.09.2023")
        then: "Error should be thrown"
            thrown(InvalidDataException)
        where:
        STARTDATE | ENDDATE
        '9.09.2023 23:00' | '8.09.2023 23:00'
        '9.09.2023 23:00' | '9.01.2024 23:00'
        '9.09.2023 23:00' | '9.09.2022 23:00'
        '9.09.2023 23:00' | '9.09.2023 22:00'
    }


//7
    def "Should delete tasks in category if category was deleted" ( ) {
        given: "There is task in category on side panel"
            Category categoryHome =  new Category("home")
            taskProviderFacade.createCategory("home")
            Task task = new Task("sprzątanie", categoryHome)
            taskProviderFacade.assignTaskToDay(task, "29.09.2023")
        when: "Trying to delete category"
            taskProviderFacade.deleteCategory(categoryHome)
        then: "Task should be deleted"
            equals(taskProviderFacade.taskExistsInSystem(task), false)
    }


//8
    def "Should hide task in planner if user used hide button on task" ( ) {
        given: "There is task on side panel"
            Task task = new Task("sprzątanie")
        and: "Task is assigned to specific day"
            taskProviderFacade.assignTaskToDay(task, "29.09.2023")
        when: "User hide task"
            taskProviderFacade.hideTask(task)
        then: "Task is not visible in specific day"
        equals(taskProviderFacade.isTaskVisible(task,"29.09.2023"), false)
    }


//9
    def "Should hide all task in planner if user used hide button on task" ( ) {
        given: "There are task on side panel assigned to same category"
            Category categoryHome = new Category("home")
            Task task = new Task("nauka", categoryHome)
            Task task2 = new Task("granie", categoryHome)
        and: "Tasks are assigned to specific day"
            taskProviderFacade.assignTaskToDay(task, "29.09.2023")
            taskProviderFacade.assignTaskToDay(task2, "29.09.2023")
        when: "User hide category"
            taskProviderFacade.hideCategory(categoryHome)
        then: "Tasks are not visible in specific day"
        equals (taskProviderFacade.isTaskVisible(task,"29.09.2023"), false )
        equals (taskProviderFacade.isTaskVisible(task2, "29.09.2023"), false )
    }


//10
    def "Should move task to other day" ( ) {
        given: "Task is assigned to specific day"
            Task task = new Task("nauka")
            taskProviderFacade.assignTaskToDay(task, "29.09.2023")
        when: "Task is moved to other day"
            taskProviderFacade.moveAssignedTask(task, "30.09.2023")
        then: "Task been moved"
        equals (taskProviderFacade.taskExistsInDay(task,"29.09.2023"), false )
        equals (taskProviderFacade.taskExistsInDay(task,"30.09.2023"), true )
    }


}
