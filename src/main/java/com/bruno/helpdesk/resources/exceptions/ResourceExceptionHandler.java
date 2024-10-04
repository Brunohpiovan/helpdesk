package com.bruno.helpdesk.resources.exceptions;

import com.bruno.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.bruno.helpdesk.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),"Object Not Found", ex.getMessage(), request.getRequestURI() );

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
        StandardError error = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Violacao de dados", ex.getMessage(), request.getRequestURI() );

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),
                "Validation Error","Erro na validaçao dos campos",request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addError(x.getField(),x.getDefaultMessage());
        }

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> cpfValidationErrors(ConstraintViolationException ex, HttpServletRequest request){

        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation Error", "Erro na validação dos campos", request.getRequestURI());

        // Itera sobre as violações da ConstraintViolationException
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String fieldName = violation.getPropertyPath().toString();  // Nome do campo que violou a validação
            String errorMessage = violation.getMessage();  // Mensagem de erro associada à violação
            errors.addError(fieldName, errorMessage);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}
