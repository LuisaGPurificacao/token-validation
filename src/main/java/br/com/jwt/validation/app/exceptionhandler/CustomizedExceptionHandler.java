package br.com.jwt.validation.app.exceptionhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
        log.error("[EXCEPTION HANDLER] Error: {}", ex.getMessage());
        return buildResponse(ex);
    }

    private static ErrorResponse buildResponse(Exception ex) {
        return ErrorResponse.builder(ex, HttpStatusCode.valueOf(500), ex.getMessage()).build();
    }

}
