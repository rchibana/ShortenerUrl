package com.vanhack.shortener.exceptions;

/**
 * @author Rodrigo Chibana on 20/01/2019
 */
public class UrlNotFoundException extends Throwable {

    private static final String MESSAGE = "Url not found";

    public UrlNotFoundException() {
        super(MESSAGE);
    }

}
