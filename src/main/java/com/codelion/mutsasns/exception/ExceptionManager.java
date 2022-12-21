package com.codelion.mutsasns.exception;

import com.codelion.mutsasns.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(MutsaAppException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error("ERROR", e.getMessage()));
    }

    @ExceptionHandler(MutsaAppException.class)
    public ResponseEntity<?> mutsaAppExceptionHandler(MutsaAppException e) {
        ErrorResult errorResult = new ErrorResult(e.getErrorcode(), e.getMessage());
        return ResponseEntity
                .status(e.getErrorcode().getStatus())
                .body(Response.error("ERROR", errorResult));
    }
}
