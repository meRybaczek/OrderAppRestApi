package order.orderap.service;

import javax.persistence.EntityNotFoundException;

public class ClientIdNotFoundException extends EntityNotFoundException {
    public ClientIdNotFoundException(Integer id) {
        super("Could not find Client id :" + id);
    }
}
