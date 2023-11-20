package com.example.Calendar.task.provider.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Task {
    String name;
    Category category;

    public Task(String name){
        this.name = name;
    }
}
