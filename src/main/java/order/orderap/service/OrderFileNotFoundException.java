package order.orderap.service;

public class OrderFileNotFoundException extends RuntimeException{
    public OrderFileNotFoundException(Integer id) {
        super("Could not find orderFile id: " + id);
    }
}
