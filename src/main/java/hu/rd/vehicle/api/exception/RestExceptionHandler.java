package hu.rd.vehicle.api.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return ResponseEntity.badRequest().body(ex.getBindingResult().getAllErrors());
    }

    /**
     * Entity validáció közben talált hiba.
     *
     * @param ex is the exception
     * @return ResponseEntity
     */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler({ VehicleNotFoundException.class })
    public ResponseEntity<Object> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }


}
