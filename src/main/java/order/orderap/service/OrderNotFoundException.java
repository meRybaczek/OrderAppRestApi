package order.orderap.service;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(Integer id) {
        super("Could not find order id :" + id);
    }
}
