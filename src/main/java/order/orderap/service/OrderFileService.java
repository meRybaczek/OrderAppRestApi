package order.orderap.service;

import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import order.orderap.repository.OrderFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderFileService {
    @Autowired
    OrderFileRepository orderFileRepo;

    public OrderFile addOrderFile(OrderFile orderFile, Integer order_id) {
        orderFile.setOrder_id(order_id);
        return orderFileRepo.save(orderFile);
    }
    @Transactional
    public void deleteById(Integer id, Integer order_id) {
        orderFileRepo.deleteById(id, order_id);
    }

    public List<OrderFile> getByOrderId(Integer id) {
        return orderFileRepo.getOrderFilesByOrderId(id);


    }




}
