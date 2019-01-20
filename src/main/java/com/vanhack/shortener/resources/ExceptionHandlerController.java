package com.vanhack.shortener.resources;

import com.vanhack.shortener.exceptions.InvalidUrlException;
import com.vanhack.shortener.exceptions.UrlNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
@ControllerAdvice
@RestController
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({InvalidUrlException.class})
    public ResponseEntity<String> invalidUrlException(InvalidUrlException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({UrlNotFoundException.class})
    public ResponseEntity<String> urlNotFoundException(UrlNotFoundException e){
        return ResponseEntity.notFound().build();
    }

}
