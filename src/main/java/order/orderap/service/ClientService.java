package order.orderap.service;

import order.orderap.model.Client;
import order.orderap.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepo;
    @Autowired
    EntityManager entityManager;

    public Client add(Client client) {
        return clientRepo.save(client);
    }

    public void deleteById(Integer id) {
        clientRepo.findById(id)
                .orElseThrow(() -> new ClientIdNotFoundException(id))
                .getOrderPdfList()
                .forEach(order -> order.setClient(null));
        // powyższe aby nie usuwały się ordery przy usuwaniu clienta i nie wyskakiwał constraint valuation
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
    public List<Client> findByClientCriteria(String clientName, String clientEmail, String nipNo, Double discount) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> cr = cb.createQuery(Client.class);
        Root<Client> root = cr.from(Client.class);

        Predicate clientNamePredicate = cb.like(root.get("clientName"), clientName);
        Predicate clientEmailPredicate = cb.like(root.get("clientEmail"), clientEmail);
        Predicate nipNoPredicate = cb.like(root.get("nipNo"), nipNo);
        Predicate discountPredicate = cb.equal(root.get("discount"), discount);

        cr.select(root).where(cb.or(clientNamePredicate,clientEmailPredicate,nipNoPredicate,discountPredicate));

        return entityManager.createQuery(cr)
                .getResultList();
    }

    // criteria solution using JPA Specification
    static Specification<Client> hasClientName(String clientName) {
        return (client, cq, cb) -> cb.like(client.get("clientName"), clientName);
    }
    static Specification<Client> hasEmail(String clientEmail) {
        return (client, cq, cb) -> cb.like(client.get("clientEmail"), clientEmail);
    }

    static Specification<Client> hasDiscount(Double discount) {
        return (client, cq, cb) -> cb.equal(client.get("discount"), discount);
    }
    public List<Client> findByClientName2(String clientName) {
        return clientRepo.findAll(hasClientName(clientName));
    }
    public List<Client> findByClientEmail(String clientEmail) {
        return clientRepo.findAll(hasEmail(clientEmail));
    }
    public List<Client> findByDiscount(Double discount) {
        return clientRepo.findAll(hasDiscount(discount));
    }

    // All criteria in one, using JPA Specification
    public List<Client> findByCriteria(String clientName,String clientEmail,Double discount) {
        return clientRepo.findAll(where(hasClientName(clientName))
                .or(hasEmail(clientEmail)
                        .or(hasDiscount(discount))));
    }

}
