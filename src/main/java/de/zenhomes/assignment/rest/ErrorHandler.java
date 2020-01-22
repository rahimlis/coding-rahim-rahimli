package de.zenhomes.assignment.rest;

import de.zenhomes.assignment.model.RestErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    private static final String REQUEST_ERROR_LOG_MESSAGE = "Error while making request.  {}";

    @Resource
    private MessageSource messageSource;

    private String getLocalizedMessage(String code) {
        return messageSource.getMessage(code, new Object[]{}, code, Locale.ENGLISH);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public RestErrorResponse handleNoSuchElementException(NoSuchElementException ex) {
        String errorUiid = UUID.randomUUID().toString();
        log.error(REQUEST_ERROR_LOG_MESSAGE, errorUiid, ex);
        return new RestErrorResponse(errorUiid,
                HttpStatus.BAD_REQUEST,
                "No such element found.");
    }
}
