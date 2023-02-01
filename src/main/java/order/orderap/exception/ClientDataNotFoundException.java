package order.orderap.exception;

import javax.persistence.EntityNotFoundException;

public class ClientDataNotFoundException extends EntityNotFoundException {
    public ClientDataNotFoundException() {
        super("Could not find Client by data ");
    }
}
