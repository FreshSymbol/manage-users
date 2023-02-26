package com.freshsymbol.manageusers.mvc.exceptions;

public class ExistingUserException extends RuntimeException {
    public ExistingUserException(String massage) {
        super(massage);
    }
}
