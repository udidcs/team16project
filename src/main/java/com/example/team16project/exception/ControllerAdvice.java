package com.example.team16project.exception;

import com.example.team16project.controller.article.ArticleController;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/*
    450 : 요청 유효성 처리 에러
    451 : 로그인 필요

 */


@RestControllerAdvice(basePackageClasses = ArticleController.class)
public class ControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Errors> exceptionhandler(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        Errors errors = new Errors();
        fieldErrors.forEach((err)->{errors.getArrayList().add(new Error(err.getField(), err.getDefaultMessage()));});
        return new ResponseEntity<Errors>(errors, HttpStatusCode.valueOf(450));
    }

    @ExceptionHandler(LoginRequiredException.class)
    public ResponseEntity<String> loginhandler(LoginRequiredException exception) {
        return new ResponseEntity<String>("로그인이 필요합니다", HttpStatusCode.valueOf(451));
    }

}
