package order.orderap.service;

import order.orderap.exception.ClientIdNotFoundException;
import order.orderap.model.Client;
import order.orderap.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static order.orderap.specification.SpecificationCriteria.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepo;

    public Client add(Client client) {
        return clientRepo.save(client);
    }

    @Transactional
    public void deleteById(Integer id) {
        clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id))
                .getOrderPdfList()
                .forEach(order -> order.setClient(null));
        clientRepo.deleteById(id);
    }

    public Client update(Client client) {
        return clientRepo.save(client);
    }

    public Client findById(Integer id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id));
    }

    public List<Client> findAllClients() {
        return clientRepo.findAll();
    }

    public List<Client> findByCriteria(String clientName, String clientEmail, Double discount) {
        Specification<Client> byName = hasClientName(clientName);
        Specification<Client> byEmail = hasEmail(clientEmail);
        Specification<Client> byDiscount = hasDiscount(discount);

        return clientRepo.findAll(where(byName).or(byEmail.or(byDiscount)));
    }

}
