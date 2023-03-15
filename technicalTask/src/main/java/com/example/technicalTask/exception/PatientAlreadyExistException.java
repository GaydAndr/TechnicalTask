package com.example.technicalTask.exception;

public class PatientAlreadyExistException extends Exception{
    public PatientAlreadyExistException(String message){
        super(message);
    }
}
