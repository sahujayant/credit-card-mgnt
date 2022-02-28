package com.example.card.credit.exception;

import com.example.card.credit.model.ApiError;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.card.credit.constant.ErrorMessage.*;

/**
 * Exception handler to validate data inputs from user.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final MethodArgumentNotValidException ex) {

        List list = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "[" + fieldError.getField() + ":" + fieldError.getRejectedValue() + "]" + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(list.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final DataIntegrityViolationException ex) {

        ApiError apiError = new ApiError(CREDIT_CARD_ALREADY_REGISTERED, HttpStatus.CONFLICT);
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final DataAccessException ex) {

        ApiError apiError = new ApiError(UNABLE_TO_PROCESS_DATA_IN_YOUR_REQUEST_INVALID_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final HttpMessageNotReadableException ex) {

        ApiError apiError = new ApiError(BAD_DATA_INPUT_PLEASE_VALIDATE_YOUR_REQUEST, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final Exception ex) {

        ApiError apiError = new ApiError(UNEXPECTED_ERROR_YOUR_REQUEST_CAN_NOT_BE_PROCESSED, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
