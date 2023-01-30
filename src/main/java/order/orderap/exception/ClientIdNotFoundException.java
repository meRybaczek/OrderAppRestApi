package order.orderap.exception;

import javax.persistence.EntityNotFoundException;

public class ClientIdNotFoundException extends EntityNotFoundException {
    public ClientIdNotFoundException(Integer id) {
        super("Could not find Client id :" + id);
    }
}
