package com.example.Calendar.task.provider.exception;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String message) {super(message);}
}
