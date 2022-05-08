package com.example.demo.controller;


import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public String handle(final ConstraintViolationException ex, Model model) {
        model.addAttribute("errors", ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet()));
        return "book-registration";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Set<String>> handle(final MethodArgumentNotValidException ex) {
        Set<String> strings = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
        return ResponseEntity.badRequest().body(strings);
    }

}
