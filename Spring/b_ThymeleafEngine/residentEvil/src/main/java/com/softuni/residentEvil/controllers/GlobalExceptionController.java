package com.softuni.residentEvil.controllers;

import javassist.tools.web.BadHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionController extends AbstractController {
    private static final String DEFAULT_ERROR_MESSAGE = "There was an error with your request.";

    @ExceptionHandler(Exception.class)
    public ModelAndView getException(RuntimeException e) {
        String errorMessage =
                e.getClass().isAnnotationPresent(ResponseStatus.class)
                        ? e.getClass().getAnnotation(ResponseStatus.class).reason()
                        : DEFAULT_ERROR_MESSAGE;

        return super.view("error", "error", errorMessage);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleResourceNotFoundException(Exception ex) {
        return super.view("error", "error", "404: Not Found!");
    }

    @ExceptionHandler(BadHttpRequest.class)
    public ModelAndView handleBadRequest(Exception ex) {
        return super.view("error", "error", "400: Bad Request!");
    }
}
