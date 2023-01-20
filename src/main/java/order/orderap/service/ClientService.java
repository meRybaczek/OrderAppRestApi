package order.orderap.service;

import order.orderap.model.Client;
import order.orderap.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepo;

    public Client addClient(Client client) {
        return clientRepo.save(client);
    }
    public void deleteById(Integer id) {
        if (!clientRepo.existsById(id))
            throw new OrderNotFoundException(id);
        clientRepo.deleteById(id);
    }
    public Client findById(Integer id) {
        return clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id));
    }
    public List<Client> findAllClients() {
        return clientRepo.findAll();
    }
    public Client findBy(String clientName, String clientEmail, String nipNo) {
        if (clientName != null)
            return clientRepo.findByClientName(clientName)
                    .orElseThrow(() -> new ClientDataNotFoundException(clientName));
        else if (clientEmail != null)
            return clientRepo.findByClientEmail(clientEmail)
                    .orElseThrow(() -> new ClientDataNotFoundException(clientEmail));
        else if (nipNo != null)
            return clientRepo.findByNipNo(nipNo)
                .orElseThrow(() -> new ClientDataNotFoundException(nipNo));

        return null;                // JAK TO ZROBIC LEPIEJ
    }

    @Transactional
    public Client updateDataById(Integer id, String clientName, String clientEmail, String nipNo, Double discount) {
        if (clientRepo.findById(id).isEmpty())
            throw new ClientIdNotFoundException(id);

        if (clientName != null)
            clientRepo.updateClientName(clientName, id);
        else if (clientEmail != null)
            clientRepo.updateClientEmail(clientEmail, id);
        else if (discount != null)
            clientRepo.updateDiscount(discount, id);
        else if (nipNo != null)
            clientRepo.updateClientNipNo(nipNo, id);

        return findById(id);
    }

}
