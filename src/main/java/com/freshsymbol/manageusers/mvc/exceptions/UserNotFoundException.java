package com.freshsymbol.manageusers.mvc.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String massage) {
        super(massage);
    }
}
