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

    private OrderFileRepository orderFileRepo;

    private OrderRepository orderRepo;

    public OrderFileService(OrderFileRepository orderFileRepo, OrderRepository orderRepo) {
        this.orderFileRepo = orderFileRepo;
        this.orderRepo = orderRepo;
    }

    public OrderFile findById(Integer id) {
        return orderFileRepo.findById(id)
                .orElseThrow(() -> new OrderFileNotFoundException(id));
    }

    public List<OrderFile> getByOrderId(Integer orderId) {
        // tu bym jednak uzyl orderFileRepo skoro OrderFileService
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId))
                .getOrderFiles();

// W: to chyba jednak mało wydajne, czyż nie ?
//        orderFileRepo.findAll()
//                .stream()
//                .filter(x -> x.getOrderPdf().getId() == orderId)
//                .toList();



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
        Integer orderFileId = orderFile.getId();

        // CR: ta metoda do dyskusji na spotkaniu
        Integer order_id = orderFileRepo.findById(orderFileId)
                .orElseThrow(() -> new OrderFileNotFoundException(orderFileId))
                .getOrderPdf().getId();

        OrderPdf orderPdf = orderRepo.findById(order_id)
                .orElseThrow(() -> new OrderNotFoundException(order_id));
                                                                            // zatem wystarczy samo save ??
        //orderFile.setOrderPdf(orderPdf);
        // CR: powinno samo to wystarczyc
        return orderFileRepo.save(orderFile);
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!orderFileRepo.existsById(id))
            throw new OrderFileNotFoundException(id);

        orderFileRepo.deleteById(id);
    }

}
