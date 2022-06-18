package com.carrito.app.exceptions.exceptionhandler;

import com.carrito.app.domain.dto.ExceptionResponseDto;
import com.carrito.app.exceptions.CartNotFoundException;
import com.carrito.app.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);

        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);

        return new ResponseEntity<>(exceptionResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CartNotFoundException.class})
    public final ResponseEntity<Object> handleCartNotFoundException(Exception ex, WebRequest request) throws Exception {
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(LocalDate.now(), ex.getMessage());
        request.getDescription(false);

        return new ResponseEntity<>(exceptionResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errores = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> {
                    return "El campo ".concat(error.getField()).concat(" : ").concat(error.getDefaultMessage());
                })
                .collect(Collectors.toList());

        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(LocalDate.now(), ex.getMessage(), errores);
        return new ResponseEntity<>(exceptionResponseDto, HttpStatus.BAD_REQUEST);
    }
}
