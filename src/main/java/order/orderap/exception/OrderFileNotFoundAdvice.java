package order.orderap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderFileNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrderFileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderFileNotFoundHandler(OrderFileNotFoundException ex) {
        return ex.getMessage();
    }
}
