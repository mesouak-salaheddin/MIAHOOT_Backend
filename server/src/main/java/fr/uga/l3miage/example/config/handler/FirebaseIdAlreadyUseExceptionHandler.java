package fr.uga.l3miage.example.config.handler;


import fr.uga.l3miage.example.error.DescriptionAlreadyUseErrorResponse;
import fr.uga.l3miage.example.error.ErrorResponse;
import fr.uga.l3miage.example.error.FirebaseIdAlreadyUseErrorResponse;
import fr.uga.l3miage.example.exception.rest.DescriptionAlreadyUseRestException;
import fr.uga.l3miage.example.exception.rest.FirebaseIdAlreadyExistsRestException;
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
public class FirebaseIdAlreadyUseExceptionHandler {

    @ExceptionHandler(FirebaseIdAlreadyExistsRestException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest httpServletRequest, Exception exception){
        FirebaseIdAlreadyExistsRestException ex = (FirebaseIdAlreadyExistsRestException) exception;
        final FirebaseIdAlreadyUseErrorResponse response = FirebaseIdAlreadyUseErrorResponse.builder()
                .uri(httpServletRequest.getRequestURI())
                .httpStatus(ex.getHttpStatus())
                .errorCode(ex.getErrorCode())
                .errorMessage("Cet id d'utilisateur firebase :" + ex.getFirebaseId() +  " existe déjà ")
                .fireBaseId(ex.getFirebaseId())
                .build();
        log.warn(ex.getMessage());
        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }
}



