
package com.ucb.mercadoapi.handler;

import com.ucb.mercadoapi.exception.ConflictException;
import com.ucb.mercadoapi.exception.ErrorResponse;
import com.ucb.mercadoapi.exception.NotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Registro não encontrado
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Entidade não encontrada pelo JPA
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // E-mail ou senha incorretos
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException ex) {
        return buildResponse("E-mail ou senha incorretos.", HttpStatus.UNAUTHORIZED);
    }

    // Violação de restrições do banco de dados
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrity(DataIntegrityViolationException ex) {
        return buildResponse(
                "Não foi possível realizar a operação devido a uma restrição dos dados.",
                HttpStatus.CONFLICT
        );
    }

    // Dados inválidos enviados pelo front-end
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Os dados informados são inválidos.");

        return buildResponse(mensagem, HttpStatus.BAD_REQUEST);
    }

    // JSON inválido ou incompatível
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadable(HttpMessageNotReadableException ex) {
        return buildResponse(
                "Os dados enviados estão em um formato inválido.",
                HttpStatus.BAD_REQUEST
        );
    }

    // Parâmetro com tipo incorreto
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return buildResponse(
                "O parâmetro informado possui um formato inválido.",
                HttpStatus.BAD_REQUEST
        );
    }

    // Qualquer exceção não prevista
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return buildResponse(
                "Ocorreu um erro interno no servidor.",
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Metodo auxiliar para padronizar as respostas
    private ResponseEntity<ErrorResponse> buildResponse(
            String message,
            HttpStatus status) {

        ErrorResponse response = ErrorResponse.builder()
                .message(message)
                .status(status.value())
                .build();

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflict(ConflictException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
