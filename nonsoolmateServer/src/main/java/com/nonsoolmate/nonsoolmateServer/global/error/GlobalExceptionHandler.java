package com.nonsoolmate.nonsoolmateServer.global.error;

import com.nonsoolmate.nonsoolmateServer.global.response.ErrorResponse;
import com.nonsoolmate.nonsoolmateServer.global.error.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler({ClientException.class})
    protected ResponseEntity<ErrorResponse> handleCustomException(ClientException ex) {
        return ResponseEntity.status(ex.getExceptionType().status()).body(ErrorResponse.of(ex.getExceptionType()));
    }

    @ExceptionHandler({BusinessException.class})
    protected ResponseEntity<ErrorResponse> handleServerException(BusinessException ex) {
        log.error("🚨BusinessException occurred: {} 🚨", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(CommonErrorType.INTERNAL_SERVER_ERROR));
    }

    /* 500 번대 에러에도 종류가있는데 범주가 나뉠 수 있음
    컨테이너가 떨어짐 -> 요청이 안들어가 -> 이런건 서버가 죽은거니까 클라가 처리가 해주어야함
            이거에 따라서 503이런거를 내려줘서 서버가 지금 안된다라는 페이지를 띄우거나 처리를 해줘야함
    Service Unavailable
    */
    /* 서버 자체가 죽어버린 경우에는 Service Unavailable, 서버와 통신하는 외부 서비스에 문제가 있는 경우에는 500
    S3에서 장애가 난 경우에는 500을 내려주고, 서버 자체가 죽었을 때에 대해서는 nginx에서 처리
    */

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<ErrorResponse> handleServerException(Exception ex) {
        log.error("🚨 InternalException occurred: {} 🚨", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(CommonErrorType.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(final NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(CommonErrorType.NOT_FOUND_PATH, ex.getRequestURL()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(CommonErrorType.INVALID_INPUT_VALUE, builder.toString()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(HttpMediaTypeNotSupportedException ex){
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(ErrorResponse.of(CommonErrorType.INVALID_JSON_TYPE, ex.getMessage()));
    }
}
