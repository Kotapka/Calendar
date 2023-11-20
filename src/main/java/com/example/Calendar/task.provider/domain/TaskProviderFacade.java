package com.example.Calendar.task.provider.domain;

public class TaskProviderFacade {
    public Task assignTaskToDay(Task task, String date){
        return Task.builder().build();
    }
    public Task getTasksFromDay(Task task, String date){
        return Task.builder().build();
    }
    public Task editTaskCategory(Task task,Category category){
        return Task.builder().build();
    }
    public Task editTaskName(Task task, String name){
        return Task.builder().build();
    }
    public Task deleteTask(Task task){
        return Task.builder().build();
    }
    public Boolean taskExistsInSystem(Task task){
        return true;
    }
    public Boolean taskExistsInDay(Task task,String Day){
        return true;
    }
    public Category editCategoryName(Category category,String name){
        return Category.builder().build();
    }
    public Task unassigneTask(Task task,String day){
        return Task.builder().build();
    }
    public Task editAssignedTaskDate(Task task, String startDate, String endDate){
        return Task.builder().build();
    }
    public Category createCategory(String name){
        return Category.builder().build();
    }
    public Category deleteCategory(Category category){
        return Category.builder().build();
    }
    public Task hideTask(Task task){
        return Task.builder().build();
    }
    public Task hideCategory(Category category){
        return Task.builder().build();
    }
    public boolean isTaskVisible(Task task,String day){
        return true;
    }
    public Task moveAssignedTask(Task task,String day){
        return Task.builder().build();
    }
}
