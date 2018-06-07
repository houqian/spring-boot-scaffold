package org.houqian.springbootdemo.advice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @author : houqian
 * @version : 1.0
 * @since : 2018/6/7
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


  @ExceptionHandler(Exception.class)
  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
            request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
    return new ResponseEntity<>(errorDetails, HttpStatus.OK);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<ErrorDetails> handleParamExceptions(Exception ex, WebRequest request) {

    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
            request.getDescription(false), 800);
    log.error("==============================参数异常================================");
    log.error(errorDetails.toString());
    return new ResponseEntity<>(errorDetails, HttpStatus.OK);
  }


  @NoArgsConstructor
  @Data
  @AllArgsConstructor
  public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private int code;
  }
}