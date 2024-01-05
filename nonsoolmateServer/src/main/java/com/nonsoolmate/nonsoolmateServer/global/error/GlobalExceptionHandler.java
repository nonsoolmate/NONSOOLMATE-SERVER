package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.common.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(BusinessException ex) {
        log.error("🚨 BusinessException occured: {} 🚨", ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.of(ex.getExceptionType()));
    }

    @ExceptionHandler({Exception.class})
    protected ApiResponse handleServerException(Exception ex) {
        log.error(ex.getMessage());
        return ApiResponse.error(ErrorType.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ApiResponse handleNotFoundException(final NoHandlerFoundException ex) {
        HashMap<String, String> pathMap = new HashMap<>();
        pathMap.put("path", ex.getRequestURL());
        return ApiResponse.error(ErrorType.NOT_FOUND_PATH, pathMap);
    }
}
