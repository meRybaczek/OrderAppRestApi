package order.orderap.specification;

import order.orderap.model.Client;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationCriteria {

    public static Specification<Client> hasClientName(String clientName) {
        return (client, cq, cb) -> cb.like(client.get("clientName"), clientName);
    }

    public static Specification<Client> hasEmail(String clientEmail) {
        return (client, cq, cb) -> cb.like(client.get("clientEmail"), clientEmail);
    }

    public static Specification<Client> hasDiscount(Double discount) {
        return (client, cq, cb) -> cb.equal(client.get("discount"), discount);
    }
}
