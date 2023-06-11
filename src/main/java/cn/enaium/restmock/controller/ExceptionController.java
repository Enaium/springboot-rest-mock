package cn.enaium.restmock.controller;

import cn.enaium.restmock.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Enaium
 */
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        if (e instanceof ServiceException) {
            return ResponseEntity.status(((ServiceException) e).getStatus()).body(e.getMessage());
        }
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
