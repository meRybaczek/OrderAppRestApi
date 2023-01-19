package order.orderap.service;

import javax.persistence.EntityNotFoundException;

public class ClientDataNotFoundException extends EntityNotFoundException {
    public ClientDataNotFoundException(String data) {
        super("Could not find Client data :" + data);
    }
}
