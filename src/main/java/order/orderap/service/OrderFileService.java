package order.orderap.service;

import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import order.orderap.repository.OrderFileRepository;
import order.orderap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderFileService {
    @Autowired
    OrderFileRepository orderFileRepo;
    @Autowired
    OrderRepository orderRepo;

    public OrderFile addOrderFile(OrderFile orderFile, Integer order_id) {
        if(orderRepo.findById(order_id).isEmpty())
            throw new OrderNotFoundException(order_id);
        else {
            orderFile.setOrder_id(order_id);
            return orderFileRepo.save(orderFile);
        }
    }

    public OrderFile findById(Integer id) {
        return orderFileRepo.findById(id)
                .orElseThrow(()-> new OrderFileNotFoundException(id));
    }
//    @Transactional
//    public void deleteById(Integer id, Integer order_id) {
//   //     if (orderFileRepo.findById(id).isPresent())
//        orderFileRepo.deleteById(id, order_id);
//    }

    public List<OrderFile> getByOrderId(Integer id) {
        if (orderRepo.findById(id).isEmpty())
            throw new OrderNotFoundException(id);
        else
            return orderFileRepo.getOrderFilesByOrderId(id);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (orderFileRepo.findById(id).isEmpty())
            throw new OrderFileNotFoundException(id);
        else
            orderFileRepo.deleteById(id);
    }

}
