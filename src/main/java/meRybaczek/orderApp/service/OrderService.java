package meRybaczek.orderApp.service;

import meRybaczek.orderApp.exception.OrderNotFoundException;
import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.model.OrderFile;
import meRybaczek.orderApp.repository.ClientRepository;
import meRybaczek.orderApp.repository.OrderRepository;
import meRybaczek.orderApp.exception.ClientDataNotFoundException;
import meRybaczek.orderApp.exception.ClientIdNotFoundException;
import meRybaczek.orderApp.model.OrderPdf;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepo, ClientRepository clientRepo) {
        this.orderRepository = orderRepo;
        this.clientRepository = clientRepo;
    }

    public OrderPdf findById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    public List<OrderPdf> getAll(Integer clientId, String clientName, LocalDate createdAt) {

        // CR: w tej metodzie nie stosujesz juz specification?
        // CR: czemu tu if w nawiasach a dalej nie?
        if (clientName != null) {
            return clientRepository.findByClientName(clientName)
                    .orElseThrow(ClientDataNotFoundException::new)
                    .getOrderPdfList();
        }
        else if (createdAt != null)
            return orderRepository.findByCreatedAt(createdAt);

        else if (clientId != null)
            return clientRepository.findById(clientId)
                    .orElseThrow(() -> new ClientIdNotFoundException(clientId))
                    .getOrderPdfList();

        return orderRepository.findAll();
    }

    @Transactional
    public OrderPdf add(OrderPdf order, Integer clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientIdNotFoundException(clientId));

        order.setClient(client);

        for (OrderFile orderFile : order.getOrderFiles()) {
            orderFile.setOrderPdf(order);
        }

        return orderRepository.save(order);
    }

    @Transactional
    public void delete(Integer id) {
        if (!orderRepository.existsById(id))
            throw new OrderNotFoundException(id);

        orderRepository.deleteById(id);
    }

}