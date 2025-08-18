package com.tave.attendance.global.common.advice;

import com.tave.attendance.global.common.exception.BaseException;
import com.tave.attendance.global.common.exception.ErrorCode;
import com.tave.attendance.global.common.exception.ErrorDetail;
import com.tave.attendance.global.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

import static com.tave.attendance.global.common.exception.ErrorCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT = "Class : {}, Code : {}, Message : {}";

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException e) {
        logWarning(e, e.getStatus().value());
        return responseException(e.getStatus(), e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<ErrorDetail>>> handleMethodArgumentValidation(MethodArgumentNotValidException e) {
        ErrorCode errorCode = METHOD_ARGUMENT_NOT_VALID;

        List<ErrorDetail> errors = e.getBindingResult()
                .getFieldErrors().stream()
                .map(fe -> ErrorDetail.of(
                        fe.getField(),
                        fe.getDefaultMessage(),
                        fe.getRejectedValue()
                ))
                .toList();

        logWarning(e, errorCode.getStatus().value());
        return responseException(errorCode.getStatus(), errorCode.getMessage(), errors);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNoResourceFound(NoResourceFoundException e) {
        ErrorCode errorCode = RESOURCE_NOT_FOUND;
        logWarning(e, errorCode.getStatus().value());
        return responseException(errorCode.getStatus(), errorCode.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        ErrorCode errorCode = INTERNAL_SERVER_ERROR;
        logWarning(e, errorCode.getStatus().value());
        return responseException(errorCode.getStatus(), e.getMessage(), null);
    }

    private <T> ResponseEntity<ApiResponse<T>> responseException(HttpStatus status, String message, T data ) {
        ApiResponse<T> response = ApiResponse.response(status, message, data);

        return ResponseEntity
                .status(status)
                .body(response);
    }

    private void logWarning(Exception e, int errorCode) {
        log.warn(e.getMessage(), e);
        log.warn(LOG_FORMAT, e.getClass().getSimpleName(), errorCode, e.getMessage());
    }

}
