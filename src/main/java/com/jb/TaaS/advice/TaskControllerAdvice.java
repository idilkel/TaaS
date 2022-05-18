package com.jb.TaaS.advice;

import com.jb.TaaS.exceptions.CustomTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskControllerAdvice {
    @ExceptionHandler(value = {CustomTaskException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDetails handleError(Exception e) {
        return new ErrDetails("Taas", e.getMessage());
    }
}
