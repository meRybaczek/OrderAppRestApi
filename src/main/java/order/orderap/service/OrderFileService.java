package order.orderap.service;

import order.orderap.exception.OrderFileNotFoundException;
import order.orderap.exception.OrderNotFoundException;
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

    @Transactional
    public OrderFile add(OrderFile orderFile, Integer order_id) {
        OrderPdf orderPdf = orderRepo.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException(order_id));

        orderFile.setOrderPdf(orderPdf);
        return orderFileRepo.save(orderFile);
    }

    public OrderFile update(OrderFile orderFile) {
        Integer orderFileId = orderFile.getId();

        Integer order_id = orderFileRepo.findById(orderFileId)
                .orElseThrow(() -> new OrderFileNotFoundException(orderFileId))
                .getOrderPdf().getId();

        OrderPdf orderPdf = orderRepo.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException(order_id));

        orderFile.setOrderPdf(orderPdf);
        return orderFileRepo.save(orderFile);
    }


    public OrderFile findById(Integer id) {
        return orderFileRepo.findById(id)
                .orElseThrow(() -> new OrderFileNotFoundException(id));
    }

    public List<OrderFile> getByOrderId(Integer orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId))
                .getOrderFiles();
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!orderFileRepo.existsById(id))
            throw new OrderFileNotFoundException(id);

        orderFileRepo.deleteById(id);
    }

}
