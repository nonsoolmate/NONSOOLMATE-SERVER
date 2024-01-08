package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<ApiResponse> handleCustomException(BusinessException ex) {
        log.error("🚨 BusinessException occured: {} 🚨", ex.getMessage());
        return ResponseEntity.status(ex.getExceptionType().status()).body(ApiResponse.error(ex.getExceptionType()));
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ApiResponse> handleServerException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ApiResponse.error(CommonErrorType.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ApiResponse> handleNotFoundException(final NoHandlerFoundException ex) {
        HashMap<String, String> pathMap = new HashMap<>();
        pathMap.put("path", ex.getRequestURL());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error(CommonErrorType.NOT_FOUND_PATH, pathMap));
    }
}
