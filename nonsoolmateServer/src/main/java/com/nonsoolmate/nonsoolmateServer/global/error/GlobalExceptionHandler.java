package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.common.response.ApiResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.*;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ BusinessException.class })
    protected ApiResponse handleCustomException(BusinessException ex) {
        return ApiResponse.error(ex.getError());
    }

    @ExceptionHandler({ Exception.class })
    protected ApiResponse handleServerException(Exception ex) {
        log.error(ex.getMessage());
        return ApiResponse.error(Error.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ApiResponse<HashMap> handleNotFoundException(NoHandlerFoundException ex) {
        HashMap<String,String> pathMap = new HashMap<>();
        pathMap.put("path",ex.getRequestURL());
        return ApiResponse.error(Error.NOT_FOUND_PATH, pathMap);
    }
}
