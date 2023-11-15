
package com.diegosantos.workshopmongo.resources.exception;



import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import com.diegosantos.workshopmongo.services.exception.ObjectNotFoundException;

import reactor.core.publisher.Mono;

@Order(-2) 
//o objetivo é garantir que o ControllerAdvice seja avaliado antes de outros manipuladores de exceções que podem estar registrados no contexto
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandartError> handleObjectNotFoundException(ObjectNotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), "");
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler({WebExchangeBindException.class, ServerWebInputException.class})
    public Mono<ResponseEntity<StandartError>> handleValidationException(Exception ex) {
        StandartError err = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro de validação", ex.getMessage(), "");
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<StandartError> handleResponseStatusException(ResponseStatusException ex) {
        StandartError err = new StandartError(System.currentTimeMillis(), ex.getStatus().value(), "Erro de validação", ex.getMessage(), "");
        return ResponseEntity.status(ex.getStatus()).body(err);
    }
}




/*
 * 
 * 
 * 
 * 
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.reactive.function.server.ServerRequest; // Import
 * correto
 * 
 * import
 * com.diegosantos.workshopmongo.services.exception.ObjectNotFoundException;
 * 
 * @ControllerAdvice public class ResourceExceptionHandler {
 * 
 * @ExceptionHandler(ObjectNotFoundException.class) public
 * ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e,
 * ServerRequest request) { HttpStatus status = HttpStatus.NOT_FOUND;
 * StandartError err = new StandartError(System.currentTimeMillis(),
 * status.value(), "Não encontrado", e.getMessage(), ""); return
 * ResponseEntity.status(status).body(err); } }
 * 
 * 
 * 
 * 
 */