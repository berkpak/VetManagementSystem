package dev.patika.vetSystem.core.config;

import dev.patika.vetSystem.core.exception.AlreadyExistsException;
import dev.patika.vetSystem.core.exception.NotFoundException;
import dev.patika.vetSystem.core.result.Result;
import dev.patika.vetSystem.core.result.ResultData;
import dev.patika.vetSystem.core.utilies.ResultHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultData<List<String>>> handleValidationErrors(MethodArgumentNotValidException e){
        List<String> validationErrorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

       // ResultData<List<String>> resultData = new ResultData<>(false, Msg.VALIDATE_ERROR,"400",validationErrorList)
                return new ResponseEntity<>(ResultHelper.validateError(validationErrorList), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<Result> handleNotFoundException(NotFoundException e){
            return new ResponseEntity<>(ResultHelper.notFoundError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Result> handleAlreadyExist(AlreadyExistsException e){
        return new ResponseEntity<>(ResultHelper.alreadyExist(e.getMessage()), HttpStatus.CONFLICT);
    }


}
