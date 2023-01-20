package order.orderap.service;

import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import order.orderap.repository.OrderFileRepository;
import order.orderap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderFileService {
    @Autowired
    OrderFileRepository orderFileRepo;
    @Autowired
    OrderRepository orderRepo;

    @Transactional
    public OrderFile addOrderFile(OrderFile orderFile, Integer order_id) {
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
                .orElseThrow(()-> new OrderNotFoundException(orderId))
                .getOrderFiles();


//                                                                      //moze byc ??? która metody optymalniejsza
//        Optional<OrderPdf> byId = orderRepo.findById(orderId);
//        if (byId.isEmpty())
//            throw new OrderNotFoundException(orderId);
//
//        return orderFileRepo.findByOrderPdf(byId.get());
    }

    @Transactional
    public void deleteById(Integer id) {
        if (!orderFileRepo.existsById(id))
            throw new OrderFileNotFoundException(id);

        orderFileRepo.deleteById(id);
    }

}
