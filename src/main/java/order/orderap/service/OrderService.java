package order.orderap.service;

import order.orderap.exception.ClientDataNotFoundException;
import order.orderap.exception.ClientIdNotFoundException;
import order.orderap.exception.OrderNotFoundException;
import order.orderap.model.Client;
import order.orderap.model.OrderFile;
import order.orderap.model.OrderPdf;
import order.orderap.repository.ClientRepository;
import order.orderap.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepo;
    @Autowired
    ClientRepository clientRepo;


    public OrderPdf addOrder(OrderPdf order, Integer clientId) {
        Client client = clientRepo.findById(clientId)
                .orElseThrow(() -> new ClientIdNotFoundException(clientId));

        order.setClient(client);

        for (OrderFile orderFile : order.getOrderFiles()) {
            orderFile.setOrderPdf(order);
        }

        return orderRepo.save(order);
    }

    public void deleteById(Integer id) {
        if (!orderRepo.existsById(id))
            throw new OrderNotFoundException(id);

        orderRepo.deleteById(id);
    }

    public List<OrderPdf> getAllOrdersBy(String clientName, LocalDate createdAt) {

        if (clientName != null) {
            return clientRepo.findByClientName(clientName)
                    .orElseThrow(() -> new ClientDataNotFoundException(clientName))
                    .getOrderPdfList();

        } else if (createdAt != null)
            return orderRepo.findByCreatedAt(createdAt);

        return orderRepo.findAll();
    }

    public List<OrderPdf> getByClientId(Integer clientId) {
        return clientRepo.findById(clientId)
                .orElseThrow(() -> new ClientIdNotFoundException(clientId))
                .getOrderPdfList();
    }

    public OrderPdf findById(Integer id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }
}