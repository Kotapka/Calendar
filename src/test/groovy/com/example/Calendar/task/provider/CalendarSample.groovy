package com.example.Calendar.task.provider

import com.example.Calendar.task.provider.domain.Category
import com.example.Calendar.task.provider.domain.Task


trait CalendarSample {
    Category schoolCategorySample
    Task taskWithoutCategorySample
    Task taskWithCategorySample
    String toLongTaskNameSample
    String tooLongCategoryNameSample
    String sampleTaskName
    Task sampleDayWithTask //todo

    def Task createTaskSample(){

    }

    def boolean equalsCategory(Category category1, Category category2){
        //todo
    }

    def boolean equalsCategoryName(String name1, String name2){
        //todo
    }

    def boolean equalsTask(Task task1, Task task){
        //todo
    }

    def boolean equalsTaskName(String name1, String name2){
        //todo
    }

    def boolean equalsDays(Task task,Task task2){

    }

    def boolean equals(Boolean aBoolean, Boolean bBoolean){
    }
}
