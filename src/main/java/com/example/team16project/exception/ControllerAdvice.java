package com.example.team16project.exception;

import com.example.team16project.controller.article.ArticleController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackageClasses = ArticleController.class)
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> exceptionhandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        Errors errors = new Errors();
        fieldErrors.forEach((err)->{errors.getArrayList().add(new Error(err.getField(), err.getDefaultMessage()));});
        return ResponseEntity.status(400).body(errors);
    }
}
