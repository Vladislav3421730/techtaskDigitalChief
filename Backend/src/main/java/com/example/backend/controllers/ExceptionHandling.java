package com.example.backend.controllers;

import com.example.backend.exceptions.AppError;
import com.example.backend.exceptions.CreateIndexException;
import com.example.backend.exceptions.LoadDataFromDbToIndexException;
import com.example.backend.exceptions.SearchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(CreateIndexException.class)
    public ResponseEntity<AppError> handlingCreateIndexException(CreateIndexException createIndexException){
        AppError appError=new AppError(createIndexException.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(LoadDataFromDbToIndexException.class)
    public ResponseEntity<AppError> LoadDataFromDbToIndexException(LoadDataFromDbToIndexException loadDataFromDbToIndexException){
        AppError appError=new AppError(loadDataFromDbToIndexException.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SearchDataException.class)
    public ResponseEntity<AppError> LoadDataFromDbToIndexException(SearchDataException searchDataException){
        AppError appError=new AppError(searchDataException.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
