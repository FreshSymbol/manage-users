package com.freshsymbol.manageusers.mvc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ApiError {
    private String massage;
    private HttpStatus status;
    private LocalDateTime localDateTime;
}