package meRybaczek.orderApp.exception;

import javax.persistence.EntityNotFoundException;

public class OrderNotFoundException extends EntityNotFoundException {
    public OrderNotFoundException(Integer id) {
        super("Could not find order id :" + id);
    }
}
