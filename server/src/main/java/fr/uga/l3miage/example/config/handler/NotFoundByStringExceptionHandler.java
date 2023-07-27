package fr.uga.l3miage.example.config.handler;


import fr.uga.l3miage.example.error.ErrorResponse;
import fr.uga.l3miage.example.error.NotFoundByStringErrorResponse;
import fr.uga.l3miage.example.exception.rest.NotFoundByStringRestException;
import fr.uga.l3miage.example.exception.technical.NotFoundByStringException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ConditionalOnWebApplication
@RequiredArgsConstructor
@ControllerAdvice
@Slf4j
public class NotFoundByStringExceptionHandler {


    @ExceptionHandler(NotFoundByStringRestException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception) {
        NotFoundByStringRestException ex = (NotFoundByStringRestException) exception;
        final NotFoundByStringErrorResponse response = NotFoundByStringErrorResponse.builder()
                .uri(httpServletRequest.getRequestURI())
                .httpStatus(ex.getHttpStatus())
                .errorMessage(ex.getMessage())
                .errorCode(ex.getErrorCode())
                .firebaseId(ex.getSearch())
                .build();


        return  ResponseEntity.status(ex.getHttpStatus()).body(response);

    }
}
