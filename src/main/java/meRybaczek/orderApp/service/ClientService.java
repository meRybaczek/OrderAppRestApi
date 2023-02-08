package meRybaczek.orderApp.service;

import meRybaczek.orderApp.model.Client;
import meRybaczek.orderApp.exception.ClientDataNotFoundException;
import meRybaczek.orderApp.exception.ClientIdNotFoundException;
import meRybaczek.orderApp.repository.ClientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static meRybaczek.orderApp.specification.SpecificationCriteria.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ClientService {

    private ClientRepository clientRepo;

    public ClientService(ClientRepository clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client findById(Integer id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id));
    }

    public List<Client> findByCriteria(String clientName, String clientEmail, Double discount) {
        if (clientName == null && clientEmail == null && discount == null)
            return clientRepo.findAll();

        Specification<Client> byName = hasClientName(clientName);
        Specification<Client> byEmail = hasEmail(clientEmail);
        Specification<Client> byDiscount = hasDiscount(discount);

        List<Client> all = clientRepo.findAll(where(byName).or(byEmail.or(byDiscount)));

        // CR: dlaczego error jak lista jest pusta? GET gdy na dla podanych warunkow nie ma danych powinien zwracac pusta liste..
        if (all.isEmpty())
            throw new ClientDataNotFoundException();

        // CR: dlaczego tu jest odpalany drugi razy findAll, chyba przeoczenie
        return clientRepo.findAll(where(byName).or(byEmail.or(byDiscount)));
    }

    public Client add(Client client) {
        return clientRepo.save(client);
    }

    @Transactional
    public Client update(Client client) {
        return clientRepo.save(client);
    }

    @Transactional
    public void delete(Integer id) {
        clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id)) // CR: rozdzielic lambde na client i potem na liste orderpdf na ktorej potem odlaczy sie referencje, nieczytelne
                .getOrderPdfList() 
                .forEach(order -> order.setClient(null));
        clientRepo.deleteById(id);
    }
    
}
