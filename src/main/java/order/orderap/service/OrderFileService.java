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

        OrderPdf orderPdf = orderRepo.findById(order_id).get();

        orderFile.setOrderPdf(orderPdf);
        return orderFileRepo.save(orderFile);

    }

    public OrderFile findById(Integer id) {
        return orderFileRepo.findById(id)
                .orElseThrow(()-> new OrderFileNotFoundException(id));
    }
    public List<OrderFile> getByOrderId(Integer id) {
        if (orderRepo.findById(id).isEmpty())
            throw new OrderNotFoundException(id);

        return orderRepo.findById(id).get().getOrderFiles();
    }

    public List<OrderFile> findByFileName(String name){
        return orderFileRepo.findByFileName(name);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!orderFileRepo.existsById(id))
            throw new OrderFileNotFoundException(id);

        orderFileRepo.deleteById(id);
    }

}
