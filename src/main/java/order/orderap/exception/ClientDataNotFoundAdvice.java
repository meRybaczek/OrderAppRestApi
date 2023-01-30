package order.orderap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ClientDataNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ClientDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String clientDataNotFoundHandler(ClientDataNotFoundException ex) {
        return ex.getMessage();
    }
}
