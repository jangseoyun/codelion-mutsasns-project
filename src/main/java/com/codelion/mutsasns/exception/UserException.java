package com.codelion.mutsasns.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException{
    private ErrorCode errorcode;
    private String message;

    @Override
    public String toString() {
        if(message == null) return errorcode.getMessage();
        return String.format("%s. %s", errorcode.getMessage(), message);
    }
}
