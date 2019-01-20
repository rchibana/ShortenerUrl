package com.vanhack.shortener.exceptions;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
public class InvalidUrlException extends Throwable {

    private static final String MESSAGE = "Invalid Url";

    public InvalidUrlException() {
        super(MESSAGE);
    }

    public InvalidUrlException(String message){
        super(message);
    }

}
