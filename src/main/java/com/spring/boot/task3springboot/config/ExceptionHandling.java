package com.spring.boot.task3springboot.config;
import com.spring.boot.task3springboot.dto.ExceptionDto;
import com.spring.boot.task3springboot.model.BundleMessage;
import com.spring.boot.task3springboot.service.BundleTranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleException(Exception exception) {
        BundleMessage bundleMessage = BundleTranslationService.getMessageArEn(exception.getMessage());
        return ResponseEntity.ok(new ExceptionDto(bundleMessage));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationException(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        String error = fieldError.getDefaultMessage();
        BundleMessage bundleMessage = BundleTranslationService.getMessageArEn(error);
        return ResponseEntity.ok(new ExceptionDto(bundleMessage));
    }
}
