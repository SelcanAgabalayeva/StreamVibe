package com.selcan.StreamVibe.exception;

import com.selcan.StreamVibe.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(
            NotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "status", 404,
                        "error", "Not Found",
                        "message", ex.getMessage()
                ));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldError()
                .getDefaultMessage();

        return ResponseEntity.badRequest()
                .body(Map.of("message", message));
    }
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> handleConflict(
            ConflictException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        Map.of(
                                "message",
                                ex.getMessage()
                        )
                );
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleUnauthorized(
            UnauthorizedException ex
    ) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ErrorResponseDto.builder()
                                .status(401)
                                .message(ex.getMessage())
                                .build()
                );
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDto> handleForbidden(
            ForbiddenException ex
    ) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ErrorResponseDto.builder()
                                .status(403)
                                .message(ex.getMessage())
                                .build()
                );
    }

}