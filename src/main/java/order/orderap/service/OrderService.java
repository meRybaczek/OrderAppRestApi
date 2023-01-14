package order.orderap.service;

import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import order.orderap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;


    public OrderPdf addOrder(OrderPdf order) {
        for (OrderFile orderFile : order.getOrderFiles()) {
            orderFile.setOrderPdf(order);
        }
        return orderRepo.save(order);
    }
    public void deleteById(Integer id) {
        if(orderRepo.findById(id).isEmpty())
            throw new OrderNotFoundException(id);
        else
            orderRepo.deleteById(id);
    }

    public List<OrderPdf> getAllOrders(String clientName,
                                       String clientPhone,
                                       LocalDate createdAt,
                                       Double clientDiscount) {

        if (clientName != null)
            return orderRepo.findByClientName(clientName);
        else if (clientPhone != null)
            return orderRepo.findByClientPhone(clientPhone);
        else if (createdAt != null)
            return orderRepo.findByCreatedAt(createdAt);
        else if (clientDiscount != null)
            return orderRepo.findByDiscount(clientDiscount);
        return orderRepo.findAll();
    }

    public OrderPdf findById(Integer id) {
        return orderRepo.findById(id)
                .orElseThrow(()-> new OrderNotFoundException(id));
    }
@Transactional
    public OrderPdf updateDiscountById (Double discount, Integer id){
        if (orderRepo.findById(id).isEmpty())
            throw new OrderNotFoundException(id);

        orderRepo.updateDiscount(discount, id);
        return findById(id);
    }
}