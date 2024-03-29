package com.sparta.project_d.exception;

import com.sparta.project_d.dto.ResponseDto;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto.Error> handlerException(CustomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(new ResponseDto.Error(e.getErrorCode().getMessage()));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseDto.Error> handlerException(Exception e) {
//        log.error(e.toString() + " occured: {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .body(new ResponseDto.Error(e.getMessage()));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto.Error> validationException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.status(ErrorCode.InValidException.getHttpStatus())
                .body(new ResponseDto.Error(errors.get(0)));
    }
}

