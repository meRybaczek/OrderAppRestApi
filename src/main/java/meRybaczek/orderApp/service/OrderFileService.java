package meRybaczek.orderApp.service;

import meRybaczek.orderApp.exception.OrderNotFoundException;
import meRybaczek.orderApp.model.OrderFile;
import meRybaczek.orderApp.repository.OrderFileRepository;
import meRybaczek.orderApp.repository.OrderRepository;
import meRybaczek.orderApp.exception.OrderFileNotFoundException;
import meRybaczek.orderApp.model.OrderPdf;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class OrderFileService {

    private final OrderFileRepository orderFileRepo;

    private final OrderRepository orderRepo;

    public OrderFileService(OrderFileRepository orderFileRepo, OrderRepository orderRepo) {
        this.orderFileRepo = orderFileRepo;
        this.orderRepo = orderRepo;
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
    public OrderFile add(OrderFile orderFile, Integer order_id) {
        OrderPdf orderPdf = orderRepo.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException(order_id));

        orderFile.setOrderPdf(orderPdf);
        return orderFileRepo.save(orderFile);
    }

    @Transactional
    public OrderFile update(OrderFile orderFile) {
        return orderFileRepo.save(orderFile);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!orderFileRepo.existsById(id))
            throw new OrderFileNotFoundException(id);

        orderFileRepo.deleteById(id);
    }

}
